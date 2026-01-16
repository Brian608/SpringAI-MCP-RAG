package org.feather.controller;

import cn.hutool.http.server.HttpServerResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.feather.service.ChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @projectName: SpringAI-MCP-RAG
 * @package: org.feather.controller
 * @className: TestController
 * @author: feather
 * @description:
 * @since: 2026-01-14 22:44
 * @version: 1.0
 */
@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @GetMapping("/test-chat")
    public String chatTest(String prompt){
        return chatService.chatTest(prompt);
    }


    @GetMapping("/stream-chat")
    public Flux<String> streamResponse(String prompt, HttpServletResponse response){
       response.setCharacterEncoding("UTF-8");
        return chatService.streamResponse(prompt);
    }

}
