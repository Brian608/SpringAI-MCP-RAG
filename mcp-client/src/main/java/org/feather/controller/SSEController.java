package org.feather.controller;

import org.feather.utils.SSEServer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @projectName: SpringAI-MCP-RAG
 * @package: org.feather.controller
 * @className: SSEController
 * @author: feather
 * @description:
 * @since: 2026-01-20 10:19 PM
 * @version: 1.0
 */
@RestController
@RequestMapping("/sse")
public class SSEController {

    /**
     * @description: 前端发送连接的请求，连接sse服务
     * @author: feather
     * @date: 2026-01-20 10:22 PM
     * @param:  * @param
     * @return: {@link String}
     **/
    @GetMapping(path = "/connect",produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter connect(@RequestParam  String userId){
       return SSEServer.connect(userId);
    }


}
