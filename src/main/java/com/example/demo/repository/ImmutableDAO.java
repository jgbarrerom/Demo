package com.example.demo.repository;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.ImmutableUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ImmutableDAO {

     private final UserRepository userRepository;

    public ImmutableDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<ImmutableUser> save(ImmutableUser user) {
        return ImmutableUser.fromEntity(userRepository.save(user.toEntity()));
    }

    public Optional<ImmutableUser> findById(Long id) {
        return userRepository.findById(id).map(ImmutableUser::fromEntity).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<ImmutableUser> findAll() {
        return userRepository.findAll().stream().map(ImmutableUser::fromEntity).filter(Optional::isPresent).map(Optional::get).toList();
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
