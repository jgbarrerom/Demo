package com.example.demo.configuration;

import org.springframework.ai.chat.client.ChatClientBuilderCustomizer;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfiguration {

    @Bean
    public ChatClientBuilderCustomizer loggingCustomizer() {
        return builder -> builder.defaultAdvisors(
                SimpleLoggerAdvisor.builder().build()
        );
    }
}
