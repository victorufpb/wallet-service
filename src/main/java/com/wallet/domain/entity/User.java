package com.wallet.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class User {
    private String id;
    private String name;
    private String cpf;
    private LocalDateTime createdAt;
}
