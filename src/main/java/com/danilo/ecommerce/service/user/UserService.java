package com.danilo.ecommerce.service.user;

import com.danilo.ecommerce.domain.authority.Authority;
import com.danilo.ecommerce.domain.user.User;
import com.danilo.ecommerce.domain.user.UserPreferences;
import com.danilo.ecommerce.dto.UserRequestDTO;
import com.danilo.ecommerce.dto.UserResponseDTO;
import com.danilo.ecommerce.repository.UserRepository;
import com.danilo.ecommerce.util.LocaleProcessor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;
    private static final Set<String> ZONE_IDS = new HashSet<>(ZoneId.getAvailableZoneIds());

    public UserResponseDTO getById(BigInteger id) {
        User foundUser = userRepository.findById(id).orElseThrow(
            UserNotFoundException::new
        );
        return new UserResponseDTO(foundUser);
    }

    public UserResponseDTO getByUsername(String username) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(
            UserNotFoundException::new
        );
        return new UserResponseDTO(foundUser);
    }

    public UserResponseDTO getByPhoneNumber(String phoneNumber) {
        User foundUser = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(
            UserNotFoundException::new
        );
        return new UserResponseDTO(foundUser);
    }

    public UserResponseDTO getByEmail(String email) {
        User foundUser = userRepository.findByEmail(email).orElseThrow(
            UserNotFoundException::new
        );
        return new UserResponseDTO(foundUser);
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userDTO) {
        String encryptedPassword = passwordEncoder.encode(userDTO.password());

        User user = User.builder()
            .username(userDTO.username())
            .password(encryptedPassword)
            .fullName(userDTO.fullName())
            .phoneNumber(userDTO.phoneNumber())
            .email(userDTO.email())
            .enabled(userDTO.enabled())
            .build();

        userRepository.saveAndFlush(user);
        entityManager.refresh(user);

        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setUser(user);
        userPreferences.setCurrency(userDTO.currency());

        if (userDTO.locale() != null) {
            if (LocaleProcessor.isValid(userDTO.locale())) {
                userPreferences.setLocale(userDTO.locale());
            } else {
                throw new IllegalArgumentException("Invalid locale: " + userDTO.locale());
            }
        }

        if (userDTO.timeZone() != null) {
            if (ZONE_IDS.contains(userDTO.timeZone())) {
                userPreferences.setTimeZone(ZoneId.of(userDTO.timeZone()));
            } else {
                throw new IllegalArgumentException("Invalid time zone: " + userDTO.timeZone());
            }
        }

        user.setPreferences(userPreferences);

        if (userDTO.authorities() != null) {
            for (String value : userDTO.authorities()) {
                Authority authority = new Authority();
                authority.setAuthority(value);
                authority.setUser(user);
                user.addAuthority(authority);
            }
        }

        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    public void deleteUser(BigInteger id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }
}
