package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.User;
import com.example.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@Api(value="User Messaging App", description="App to post and view User messages")
public class UserController {

    private final UserService userMessageService;

    @Autowired
    public UserController(UserService userMessageService) {
        this.userMessageService = userMessageService;
    }

    @ApiOperation(value = "View a list of user messages", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<User>> getUsrMsgByUsrId(@PathVariable(value = "userId") Long userId)
            throws ResourceNotFoundException {

        List<User> userMsgs = userMessageService.getUsrMsgByUsrId(userId);
        return ResponseEntity.ok().body(userMsgs);

    }

    @ApiOperation(value = "Add an user")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@ApiParam(value = "User object store in database table", required = true)@Valid @RequestBody User user) {

        userMessageService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

}

