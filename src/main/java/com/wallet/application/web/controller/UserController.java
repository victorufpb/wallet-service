package com.wallet.application.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.application.web.controller.dto.request.UserRequest;
import com.wallet.domain.entity.User;
import com.wallet.domain.exception.NotFoundException;
import com.wallet.domain.exception.UserAlreadyExistsException;
import com.wallet.domain.service.UserService;

@RequestMapping(value = "users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) throws UserAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequest.toDomain()));
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws NotFoundException {
		return ResponseEntity.ok(userService.getById(id));
    	
    }
}
