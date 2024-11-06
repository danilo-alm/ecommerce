package com.danilo.ecommerce.repository;

import com.danilo.ecommerce.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface UserRepository extends CrudRepository<User, BigDecimal> {
}
