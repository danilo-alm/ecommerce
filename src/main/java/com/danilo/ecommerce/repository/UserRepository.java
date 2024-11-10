package com.danilo.ecommerce.repository;

import com.danilo.ecommerce.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, BigInteger> {
    Optional<User> findByUsername(String username);
}
