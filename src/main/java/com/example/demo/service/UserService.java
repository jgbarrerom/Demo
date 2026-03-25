package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.ImmutableUser;
import com.example.demo.repository.ImmutableDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserService {

    private final ImmutableDAO immutableDAO;

    public UserService(ImmutableDAO immutableDAO) {
        this.immutableDAO = immutableDAO;
    }

    @GetMapping
    public List<ImmutableUser> getAllUsers(){
        return immutableDAO.findAll();
    }

    @PostMapping
    public ResponseEntity<ImmutableUser> createUser(@RequestBody ImmutableUser newUser){
        var savedUser = immutableDAO.save(newUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.orElseThrow().getId()).toUri();
        return ResponseEntity.created(location).body(savedUser.orElseThrow());
    }

    @GetMapping("/{id}")
    public ImmutableUser getUserById(@PathVariable Long id){
        return getUserByIdPrivate(id);
    }

    @PutMapping("/{id}")
    public ImmutableUser updateUser(@RequestBody ImmutableUser newUserData, @PathVariable Long id){
        var oldUser = immutableDAO.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        oldUser.setName(newUserData.getName());
        oldUser.setLastName(newUserData.getLastName());
        return immutableDAO.save(oldUser).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        getUserByIdPrivate(id);
        immutableDAO.deleteById(id);
    }

    private ImmutableUser getUserByIdPrivate(Long id){
        return immutableDAO.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

}
