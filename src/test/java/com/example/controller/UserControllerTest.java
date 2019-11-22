package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.User;
import com.example.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @InjectMocks
    UserController userMessageController;

    @Mock
    UserServiceImpl userMessageService;


    private final Long User_ID = 1001L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void getUsrMsgByUsrId() throws ResourceNotFoundException {
        List<User> user = Arrays.asList(
                new User(1001, "ABC", new Date()),
                new User(1001, "ABC", new Date()),
                new User(1001, "ABC", new Date())
        );
        when(userMessageService.getUsrMsgByUsrId(anyLong())).thenReturn((user));
        ResponseEntity<List<User>> responseEntity = userMessageController.getUsrMsgByUsrId(User_ID);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

    }

    @Test
    public void testCreateUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User user = new User(1001, "ABC", new Date());
        when(userMessageService.createUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> responseEntity = userMessageController.createUser(user);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);

    }


}