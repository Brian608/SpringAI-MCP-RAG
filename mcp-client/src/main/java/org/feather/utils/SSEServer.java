package org.feather.utils;

import groovy.util.logging.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;


/**
 * @projectName: SpringAI-MCP-RAG
 * @package: org.feather.utils
 * @className: SSEServer
 * @author: feather
 * @description:
 * @since: 2026-01-20 9:35 PM
 * @version: 1.0
 */
@lombok.extern.slf4j.Slf4j
@Slf4j
public class SSEServer {
    //存放所有用户
    public  static  final Map<String,SseEmitter> seeclients=new ConcurrentHashMap<>() ;

    /**
     * @description: 连接sse服务
     * @author: feather
     * @date: 2026-01-20 10:15 PM
     * @param:  * @param userId
     * @return: {@link SseEmitter}
     **/
    public static SseEmitter connect(String userId) {
        //设置超时时间，0L 表示永不过期，默认是30秒，超时未完成任务则会抛出异常
        SseEmitter sseEmitter = new SseEmitter(0L);
        //注册回调方法
        sseEmitter.onTimeout(timeoutCallback(userId));
        sseEmitter.onCompletion(completionCallback(userId));
        sseEmitter.onError(errorCallback(userId));
        seeclients.put(userId,sseEmitter);
        log.info("SSE连接成功，用户id为:{}",userId);
        return sseEmitter;

}

    public  static Consumer<Throwable> errorCallback(String userId){
            return throwable -> {
                log.error("SSE发生异常...",throwable);
                //移除用户连接
                remove(userId);
            };
    }
    public  static Runnable timeoutCallback(String userId){
        return ()->{
            log.info("SSE超时...");
            //移除用户连接
            remove(userId);
        };
    }

    public  static Runnable completionCallback(String userId){
        return ()->{
            log.info("SSE完成...");
            //移除用户连接
            remove(userId);
        };
    }

    public   static  void remove(String userId){
        seeclients.remove(userId);
        log.info("SSE连接被移除，移除用户id为:{}",userId);
    }

}
