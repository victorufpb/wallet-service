package com.wallet.domain.gateway;

import com.wallet.domain.entity.User;

public interface UserGateway {

    public User createUser(User user);
    public User getUser(String id);
}
