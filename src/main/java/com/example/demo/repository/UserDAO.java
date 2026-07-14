package com.example.demo.repository;

import com.example.demo.core.user.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    Optional<UserDTO> save(UserDTO user);

    Optional<UserDTO> update(UserDTO user, Long userId);

    Optional<UserDTO> findById(Long id);

    List<UserDTO> findAll();

    void deleteById(Long id);
}
