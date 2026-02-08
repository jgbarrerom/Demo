package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.ImmutableUser;
import com.example.demo.repository.ImmutableDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private ImmutableDAO immutableDAO;
    private UserService userService;

    @BeforeEach
    void setUp() {
        immutableDAO = mock(ImmutableDAO.class);
        userService = new UserService(immutableDAO);
    }

    @AfterEach
    void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void getAllUsers_returnsRepositoryList() {
        when(immutableDAO.findAll()).thenReturn(List.of(new ImmutableUser("Alice", "a@example.com")));
        List<ImmutableUser> users = userService.getAllUsers();
        assertEquals(1, users.size());
        verify(immutableDAO).findAll();
    }

    @Test
    void createUser_savesAndReturnsCreated() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/users");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        var input = new ImmutableUser("Bob", "b@example.com");
        var saved = Optional.of(new ImmutableUser(1L, "Bob", "b@example.com"));
        when(immutableDAO.save(input)).thenReturn(saved);

        var response = userService.createUser(input);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(saved.get(), response.getBody());
        verify(immutableDAO).save(input);
    }

    @Test
    void getUserById_existing_returnsUser() {
        var user = new ImmutableUser("Carol", "c@example.com");
        when(immutableDAO.findById(3L)).thenReturn(Optional.of(user));

        var result = userService.getUserById(3L);
        assertEquals(user, result);
    }

    @Test
    void getUserById_missing_throws() {
        when(immutableDAO.findById(99L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(99L));
    }

    @Test
    void updateUser_existing_savesUpdated() {
        var existing = new ImmutableUser(4L, "Dave", "d@old.com");
        var newData = new ImmutableUser("Dave Updated", "d@new.com");
        when(immutableDAO.findById(4L)).thenReturn(Optional.of(existing));
        when(immutableDAO.save(any(ImmutableUser.class))).thenAnswer(inv -> Optional.of(inv.getArguments()[0]));

        var updated = userService.updateUser(newData, 4L);

        ArgumentCaptor<ImmutableUser> captor = ArgumentCaptor.forClass(ImmutableUser.class);
        verify(immutableDAO).save(captor.capture());
        var savedArg = captor.getValue();

        assertEquals(4L, savedArg.getId());
        assertEquals("Dave Updated", savedArg.getName());
        assertEquals("d@new.com", savedArg.getLastName());
        assertEquals(savedArg, updated);
    }

//    @Test
//    void deleteUser_existing_deletes() {
//        var existing = new UserEntity("Eve", "e@example.com");
//        when(userRepository.findById(5L)).thenReturn(Optional.of(existing));
//
//        var response = userService.deleteUser(5L);
//        assertEquals(204, response.getStatusCodeValue());
//        verify(userRepository).deleteById(5L);
//    }

    @Test
    void deleteUser_missing_throws() {
        when(immutableDAO.findById(11L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(11L));
    }
}
