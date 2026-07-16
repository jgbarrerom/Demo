package com.example.demo.ai;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ai-chat")
public class HolaChatGPTController {

    private final ChatService chatService;
    public HolaChatGPTController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> consultaPost(@RequestParam String message){
        return chatService.consulta(message);
    }
}
