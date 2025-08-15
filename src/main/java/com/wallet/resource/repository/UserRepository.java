package com.wallet.resource.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wallet.resource.repository.dao.UserEntity;

@Repository
public interface UserRepository extends CrudRepository <UserEntity, String> {
	
}
