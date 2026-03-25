package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder(toBuilder = true)
@Entity(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String lastName;

}


