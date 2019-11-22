package com.example.service;

import com.example.dao.UserRepository;
import com.example.exception.ResourceNotFoundException;
import com.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserMessageImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userMessageService;

    User user;

    private final Long User_ID = 1001L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void testGetUser() throws ResourceNotFoundException {


        List<User> user = Arrays.asList(
                new User(1001, "ABC", new Date()),
                new User(1001, "ABC", new Date()),
                new User(1001, "ABC", new Date())
        );

        when(userRepository.findByUserId(anyLong())).thenReturn(Optional.of(user));

        List<User> user1 = userMessageService.getUsrMsgByUsrId(User_ID);
        assertNotNull(user1);
        assertEquals(user, user1);

    }

    @Test
    public void testGetUser_ResourceNotFoundException() {
        when(userRepository.findByUserId(anyLong())).thenReturn(Optional.ofNullable(null));

        assertThrows(ResourceNotFoundException.class,
                () -> userMessageService.getUsrMsgByUsrId(User_ID)
        );
    }


    @Test
    void saveUser() {
        User user = new User(1001, "ABC", new Date());

        when(userRepository.save(any(User.class))).thenReturn(user);

        User user1 = userMessageService.createUser(user);
        assertNotNull(user1);
        assertEquals(user, user1);


    }
}
