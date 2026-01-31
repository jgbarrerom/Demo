package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public void createUser(@RequestBody UserEntity newUser){
        userRepository.save(newUser);
    }

    @GetMapping("/users/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

//    @PutMapping("{id}")
//    public void updateUser(@RequestBody UserEntity newUserData, @PathVariable Long id){
//        var oldUser = userRepository.findById(id);
//
//    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

}
