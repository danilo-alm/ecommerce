package com.danilo.ecommerce.service;

import com.danilo.ecommerce.domain.user.User;
import com.danilo.ecommerce.dto.UserDTO;
import com.danilo.ecommerce.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    @Transactional
    public User createUser(UserDTO userDTO) {
        String encryptedPassword = passwordEncoder.encode(userDTO.password());

        User user = User.builder()
            .username(userDTO.username())
            .password(encryptedPassword)
            .fullName(userDTO.fullName())
            .phoneNumber(userDTO.phoneNumber())
            .email(userDTO.email())
            .enabled(userDTO.enabled())
            .build();

        if (userDTO.currency() != null) {
            user.setCurrency(userDTO.currency());
        }
        if (userDTO.language() != null) {
            user.setLanguage(userDTO.language());
        }

        userRepository.save(user);
        entityManager.refresh(user);
        return user;
    }
}
