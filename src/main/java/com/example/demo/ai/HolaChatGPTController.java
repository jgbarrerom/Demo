package com.example.demo.ai;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai-chat")
public class HolaChatGPTController {

    private final ChatService chatService;
    public HolaChatGPTController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public String consultaPost(@RequestParam String message){
        return chatService.consulta(message);
    }
}
