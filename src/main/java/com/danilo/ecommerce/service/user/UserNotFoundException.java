package com.danilo.ecommerce.service.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("user not found");
    }

}
