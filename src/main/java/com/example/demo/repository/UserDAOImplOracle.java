package com.example.demo.repository;

import com.example.demo.core.user.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImplOracle implements UserDAO {
    @Override
    public Optional<UserDTO> save(UserDTO user) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> update(UserDTO user, Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserDTO> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }
}
