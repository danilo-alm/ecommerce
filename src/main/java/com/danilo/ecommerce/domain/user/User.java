package com.danilo.ecommerce.domain.user;

import com.danilo.ecommerce.domain.authority.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_users_email", columnList = "email"),
    @Index(name = "idx_users_username", columnList = "username")
})
@DynamicInsert
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SERIAL")
    private BigInteger id;

    @Column(name = "username", columnDefinition = "VARCHAR(36) NOT NULL UNIQUE")
    private String username;

    @Column(name = "password",columnDefinition = "CHAR(60) NOT NULL")
    private String password;

    @Column(name = "enabled", columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE")
    private boolean enabled;

    @Column(name = "email", columnDefinition = "VARCHAR(254) NOT NULL UNIQUE")
    private String email;

    @Column(name = "full_name", columnDefinition = "VARCHAR(100) NOT NULL")
    private String fullName;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(15) NOT NULL UNIQUE")
    private String phoneNumber;

    @Column(name = "currency", columnDefinition = "VARCHAR(3) NOT NULL DEFAULT 'USD'")
    private String currency;

    @Column(name = "language", columnDefinition = "VARCHAR(13) NOT NULL DEFAULT 'en'")
    private String language;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "last_login", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastLogin;

    @Column(name = "failed_login_attempts", columnDefinition = "INT NOT NULL DEFAULT 0")
    private int failedLoginAttempts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Authority> authorities;

    public void addAuthority(String authorityName) {
        if (this.authorities == null) {
            this.authorities = new HashSet<>();
        }

        boolean authorityExists = authorities.stream()
            .anyMatch(auth -> auth.getAuthority().equals(authorityName));

        if (!authorityExists) {
            Authority authority = new Authority();
            authority.setUser(this);
            authority.setAuthority(authorityName);
            authorities.add(authority);
        }
    }
}
