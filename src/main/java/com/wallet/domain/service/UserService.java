package com.wallet.domain.service;

import com.wallet.domain.entity.User;
import com.wallet.domain.exception.NotFoundException;
import com.wallet.domain.exception.UserAlreadyExistsException;
import com.wallet.domain.gateway.UserGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserGateway userGateway;

    public UserService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User create(User user) throws UserAlreadyExistsException {
    	try {
    		logger.info("Creating user for id={}", user.getId());
            return userGateway.createUser(user);
    	} catch (DataIntegrityViolationException e) {
    		throw new UserAlreadyExistsException("User already exists for cpf=" + user.getCpf());
		}        
    }
    
    public User getById(String id) throws NotFoundException {
        logger.info("Retrieving user for id={}", id);

        User user = userGateway.getUser(id);
    	if(user == null) {
    		throw new NotFoundException("User not found for id="+id);
    	}
    	
    	return user;
    }
}
