package com.example.demo.configuration;

import com.example.demo.model.AuthUser;
import com.example.demo.repository.AuthUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initDatabase(AuthUserRepository authUserRepository,
                                         PasswordEncoder passwordEncoder) {
        return args -> {
            if (!authUserRepository.existsAuthUserByUsername("admin")) {
                AuthUser admin = new AuthUser("admin", passwordEncoder.encode("admin"), "USER,ADMIN");
                authUserRepository.save(admin);
                log.info("Created default auth user: admin / admin");
            }
        };
    }
}
