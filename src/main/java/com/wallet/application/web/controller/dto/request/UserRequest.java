package com.wallet.application.web.controller.dto.request;

import com.wallet.application.util.IdGenerator;
import com.wallet.domain.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.wallet.application.util.IdGenerator.generateId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String cpf;

    public User toDomain() {
        return new User(generateId(), this.name, this.cpf, LocalDateTime.now());
    }
}
