package com.example.demo.repository;

import com.example.demo.core.user.UserDTO;
import com.example.demo.core.user.UserEntity;
import com.example.demo.core.user.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDAOImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private UserDAOImpl userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAOImpl(userRepository, userMapper);
    }

    @Test
    void findAll_returnsMappedUsers() {
        var entity = new UserEntity("Alice", "Smith");
        var dto = new UserDTO(1L, "Alice", "Smith");
        when(userRepository.findAll()).thenReturn(List.of(entity));
        when(userMapper.toDTO(entity)).thenReturn(dto);

        List<UserDTO> users = userDAO.findAll();

        assertEquals(1, users.size());
        assertEquals(dto, users.get(0));
    }

    @Test
    void save_persistsAndReturnsDto() {
        var input = new UserDTO(null, "Bob", "Jones");
        var entity = new UserEntity("Bob", "Jones");
        var savedEntity = new UserEntity("Bob", "Jones");
        var savedDto = new UserDTO(1L, "Bob", "Jones");

        when(userMapper.toEntity(input)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(savedEntity);
        when(userMapper.toDTO(savedEntity)).thenReturn(savedDto);

        var result = userDAO.save(input);

        assertTrue(result.isPresent());
        assertEquals(savedDto, result.get());
        verify(userRepository).save(entity);
    }

    @Test
    void update_existingUser_updatesFields() {
        var existing = new UserEntity("Dave", "Old");
        var updateData = new UserDTO(null, "Dave Updated", "New");
        var updatedEntity = existing.withName("Dave Updated").withLastName("New");
        var updatedDto = new UserDTO(4L, "Dave Updated", "New");

        when(userRepository.findById(4L)).thenReturn(Optional.of(existing));
        when(userRepository.save(updatedEntity)).thenReturn(updatedEntity);
        when(userMapper.toDTO(updatedEntity)).thenReturn(updatedDto);

        var result = userDAO.update(updateData, 4L);

        assertTrue(result.isPresent());
        assertEquals(updatedDto, result.get());

        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(captor.capture());
        assertEquals("Dave Updated", captor.getValue().getName());
        assertEquals("New", captor.getValue().getLastName());
    }

    @Test
    void deleteById_delegatesToRepository() {
        userDAO.deleteById(7L);
        verify(userRepository).deleteById(7L);
    }
}
