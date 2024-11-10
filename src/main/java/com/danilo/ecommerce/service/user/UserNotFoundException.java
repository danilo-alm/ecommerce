package com.danilo.ecommerce.service.user;

import java.math.BigInteger;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(BigInteger id) {
        super("User with id " + id + " not found");
    }

    public UserNotFoundException(String username) {
        super("User with username " + username + " not found");
    }

}
