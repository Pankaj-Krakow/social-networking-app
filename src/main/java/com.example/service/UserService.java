package com.example.service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsrMsgByUsrId(Long userId) throws ResourceNotFoundException;

    User createUser(User user);

}

