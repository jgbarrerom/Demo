package com.example.demo.repository;

import com.example.demo.core.exception.UserNotFoundException;
import com.example.demo.core.user.UserDTO;
import com.example.demo.core.user.UserEntity;
import com.example.demo.core.user.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {

     private final UserRepository userRepository;
     private final UserMapper userMapper;

    public UserDAO(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Optional<UserDTO> save(UserDTO user) {
        var userEntity = userMapper.toEntity(user);
        return Optional.of(userMapper.toDTO(userRepository.save(userEntity)));
    }

    public Optional<UserDTO> update(UserDTO user, Long userId) {
        var oldUser =  userRepository.findById(userId).orElseThrow();
        var updatedUser = oldUser.withName(user.name()).withLastName(user.lastName());
        return Optional.of(userMapper.toDTO(userRepository.save(updatedUser)));
    }

    public Optional<UserDTO> findById(Long id) {
        return Optional.of(userMapper.toDTO(userRepository.findById(id).orElseThrow()));
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
