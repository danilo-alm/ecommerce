package com.danilo.ecommerce.repository;

import com.danilo.ecommerce.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, BigInteger> {
    Optional<User> findByUsername(String username);
}
