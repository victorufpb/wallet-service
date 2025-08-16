package com.wallet.application.web.controller;

import com.wallet.application.web.controller.dto.request.UserRequest;
import com.wallet.domain.entity.User;
import com.wallet.domain.exception.NotFoundException;
import com.wallet.domain.exception.UserAlreadyExistsException;
import com.wallet.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "users")
@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) throws UserAlreadyExistsException {
        MDC.put("request_id", UUID.randomUUID().toString());
        logger.info("Creating user for cpf={}", userRequest.getCpf());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(userRequest.toDomain()));
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws NotFoundException {
        MDC.put("request_id", UUID.randomUUID().toString());
        logger.info("Getting user for id={}", id);

        return ResponseEntity.ok(userService.getById(id));
    }
}
