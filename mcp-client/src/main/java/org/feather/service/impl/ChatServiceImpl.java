package org.feather.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;
import org.feather.service.ChatService;
import reactor.core.publisher.Flux;

/**
 * @projectName: SpringAI-MCP-RAG
 * @package: src.main.java.org.feather.service.impl
 * @className: ChatServiceImpl
 * @author: feather
 * @description:
 * @since: 2026-01-15 9:17 PM
 * @version: 1.0
 */
@Service
public class ChatServiceImpl implements ChatService {

    private static final String SYSTEM_PROMPT = "你是一个非常聪明的人工智能助手，可以帮我解决很多问题，你的名字叫小布";

    private ChatClient chatClient;
    /**
     * @description: 提示词的三大类型
     * 1. 系统提示词 System Prompt
     * 2. 用户提示词 User Prompt
     * 3. 助手提示词 Assistant Prompt
     **/

    public ChatServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem(SYSTEM_PROMPT)
                .build();
    }

    @Override
    public String chatTest(String prompt) {
      return   chatClient.prompt(prompt).call().content();

    }
    @Override
    public Flux<String> streamResponse(String prompt) {
        return chatClient.prompt(prompt).stream().content();
    }

}
