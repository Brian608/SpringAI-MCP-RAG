package org.feather.service;

import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * @projectName: SpringAI-MCP-RAG
 * @package: src.main.java.org.feather.service
 * @className: ChatService
 * @author: feather
 * @description:
 * @since: 2026-01-15 9:16 PM
 * @version: 1.0
 */
public interface ChatService {

    /**
     * @description:测试大模型交互聊天
     * @author: feather
     * @date: 2026-01-15 9:58 PM
     * @param:  * @param prompt
     * @return: {@link String}
     **/
     String chatTest(String prompt);

     /**
      * @description: 测试大模型交互聊天流式
      * @author: feather
      * @date: 2026-01-15 9:59 PM
      * @param:  * @param prompt
      * @return: {@link String}
      **/
     Flux<String> streamResponse(String prompt);


}
