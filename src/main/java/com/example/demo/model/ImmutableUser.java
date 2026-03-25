package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class ImmutableUser {
    private final Long id;
    @Setter
    private String name;
    @Setter
    private String lastName;

    public ImmutableUser(){
            this(null, null, null);
    }

    public ImmutableUser(String name, String lastName) {
        this(null, name, lastName);
    }

    public static Optional<ImmutableUser> fromEntity(UserEntity entity) {
        return Optional.of(new ImmutableUser(entity.getId(), entity.getName(), entity.getLastName()));
    }

    public UserEntity toEntity() {
        return new UserEntity(getId(),getName(), getLastName());
    }
}
