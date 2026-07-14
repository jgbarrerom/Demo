package com.example.demo.core.user;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    @With
    private String name;

    @Column(nullable = false)
    @With
    private String lastName;

    @Version
    private Integer version;//control de concurrencia automatico

    public UserEntity(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

}
