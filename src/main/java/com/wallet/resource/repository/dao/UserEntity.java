package com.wallet.resource.repository.dao;

import java.time.LocalDateTime;

import com.wallet.domain.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
	@Id
	private String id;
	private String name;
	private String cpf;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public UserEntity(User user) {
		id = user.getId();
		name = user.getName();
		cpf = user.getCpf();
		createdAt = user.getCreatedAt();
		updatedAt = LocalDateTime.now();
	}
	
	public User toDomain() {
		return new User(id, name, cpf, createdAt);
	}
}
