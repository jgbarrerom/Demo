package com.example.demo.repository;

import com.example.demo.core.auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);

    boolean existsAuthUserByUsername(String username);

    default void useTestPrivateMethod(){
        testPrivate("Hello from default method in AuthUserRepository");
    }

    default void useTestPrivateMethod2(){
        testPrivate("Hello from second method in AuthUserRepository");
    }

    private void testPrivate(String message){
        System.out.println("test message: " + message);
    }
}
