package com.example.demo.rest;

import com.example.demo.core.user.UserDTO;
import com.example.demo.repository.UserDAO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userDAO.findAll();
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO newUser) {
        var savedUser = userDAO.save(newUser).orElseThrow();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.id())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userDAO.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@Valid @RequestBody UserDTO newUserData, @PathVariable Long id) {
        return userDAO.update(newUserData, id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDAO.deleteById(id);
    }
}
