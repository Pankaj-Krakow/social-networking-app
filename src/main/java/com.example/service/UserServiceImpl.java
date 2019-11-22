package com.example.service;

import com.example.dao.UserRepository;
import com.example.exception.ResourceNotFoundException;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsrMsgByUsrId(Long userId) throws ResourceNotFoundException {
        List<User> userMsgs = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found" + userId));

        userMsgs.sort((User s1, User s2) -> (int) (s2.getId() - s1.getId()));
        return userMsgs;

    }

    @Override
    public User createUser(User user) {
        user.setTimestamp(new Date());
        return userRepository.save(user);
    }


}


