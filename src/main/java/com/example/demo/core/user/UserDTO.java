package com.example.demo.core.user;

import jakarta.validation.constraints.NotNull;

public record UserDTO(
        Long id,
        @NotNull(message = "Name should not be null") String name,
        @NotNull String lastName
) {
}
