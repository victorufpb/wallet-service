package com.wallet.resource.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wallet.domain.entity.User;
import com.wallet.domain.gateway.UserGateway;
import com.wallet.resource.repository.UserRepository;
import com.wallet.resource.repository.dao.UserEntity;

@Component
public class UserGatewayImpl implements UserGateway {

    @Autowired
    public UserGatewayImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	private final UserRepository userRepository;
	
	@Override
	public User createUser(User user) {
		return userRepository.save(new UserEntity(user)).toDomain();
	}

	@Override
	public User getUser(String id) {
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		return userEntity != null ? userEntity.toDomain() : null;
	}

}
