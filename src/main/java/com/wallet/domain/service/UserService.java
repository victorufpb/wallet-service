package com.wallet.domain.service;

import com.wallet.domain.entity.User;
import com.wallet.domain.exception.NotFoundException;
import com.wallet.domain.exception.UserAlreadyExistsException;
import com.wallet.domain.gateway.UserGateway;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserGateway userGateway;

    public UserService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User create(User user) throws UserAlreadyExistsException {
    	try {
    		System.out.println("Creating user" + user);
            return userGateway.createUser(user);
    	} catch (DataIntegrityViolationException e) {
    		throw new UserAlreadyExistsException("User already exists for cpf=" + user.getCpf());
		}        
    }
    
    public User getById(String id) throws NotFoundException {
    	User user = userGateway.getUser(id);
    	if(user == null) {
    		throw new NotFoundException("User not found for id="+id);
    	}
    	
    	return user;
    }
}
