package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity(name = "USERS")
@Getter
@ToString
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String lastName;

    public UserEntity(String name, String lastName){
        this.name = name;
        this.lastName = lastName;
    }
}
