package com.danilo.ecommerce.repository;

import com.danilo.ecommerce.domain.authority.Authority;
import com.danilo.ecommerce.domain.user.User;
import com.danilo.ecommerce.domain.user.UserPreferences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private final String testUsername = "testuser";

    @BeforeEach
    public void setUp() {
        testUser = User.builder()
            .username(testUsername)
            .password("rawpassword")
            .fullName("Test User")
            .email("test@email.com")
            .phoneNumber("1234567890")
            .enabled(true)
            .build();

        UserPreferences testUserPreferences = UserPreferences.builder()
            .user(testUser)
            .locale(Locale.ENGLISH)
            .currency(Currency.getInstance("USD"))
            .timeZone(ZoneId.of("GMT"))
            .build();

        testUser.setPreferences(testUserPreferences);

        testUser.addAuthority(
            Authority.builder()
                .authority("ROLE_ADMIN")
                .user(testUser)
                .build()
        );
        testUser.addAuthority(
            Authority.builder()
                .authority("ROLE_USER")
                .user(testUser)
                .build()
        );
    }

    @Test
    public void UserRepository_SaveUser_ReturnsUserWithId() {
        User savedUser = userRepository.save(testUser);
        assertThat(savedUser.getId()).isGreaterThan(BigInteger.ZERO);
    }

    @Test
    public void UserRepository_UpdateUser_UpdatesUserInDB() {
        userRepository.save(testUser);

        String newUsername = "newusername";
        testUser.setUsername(newUsername);
        userRepository.save(testUser);

        Optional<User> optUpdatedUser = userRepository.findById(testUser.getId());
        assertThat(optUpdatedUser).isNotEmpty();

        assertThat(optUpdatedUser.get().getId()).isEqualTo(testUser.getId());
        assertThat(optUpdatedUser.get().getUsername()).isEqualTo(newUsername);
    }

    @Test
    public void UserRepository_FindByUsername_ReturnsUserWithUsername() {
        userRepository.save(testUser);

        Optional<User> optFoundUser = userRepository.findByUsername(testUsername);
        assertThat(optFoundUser).isNotEmpty();

        User foundUser = optFoundUser.get();
        assertThat(foundUser.getUsername()).isEqualTo(testUsername);
        assertThat(foundUser.getAuthorities().isEmpty()).isFalse();
        assertThat(foundUser.getAuthorities().size()).isEqualTo(2);

        assertThat(optFoundUser.get().getId()).isGreaterThan(BigInteger.ZERO);
    }

    @Test
    public void UserRepository_FindByUsername_ReturnsNoUser() {
        userRepository.save(testUser);
        Optional<User> foundUser = userRepository.findByUsername("random");

        assertThat(foundUser).isEmpty();
    }

    @Test
    public void UserRepository_DeleteUser_DeletesFromDB() {
        userRepository.save(testUser);

        userRepository.delete(testUser);
        Optional<User> deletedUser = userRepository.findByUsername(testUser.getUsername());

        assertThat(deletedUser).isEmpty();
    }
}
