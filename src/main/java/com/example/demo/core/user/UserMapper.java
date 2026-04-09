package com.example.demo.core.user;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity userEntity) {
        return new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getLastName());
    }

    public UserEntity toEntity(UserDTO userDTO) {
        return new UserEntity(userDTO.name(), userDTO.lastName());
    }
}
