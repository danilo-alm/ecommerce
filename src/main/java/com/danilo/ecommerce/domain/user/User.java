package com.danilo.ecommerce.domain.user;

import com.danilo.ecommerce.domain.authority.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users", indexes = {
    @Index(name = "IX_Users_Email", columnList = "Email"),
    @Index(name = "IX_Users_Username", columnList = "Username"),
    @Index(name = "IX_Users_PhoneNumber", columnList = "PhoneNumber")
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
    @Column(name = "Id", columnDefinition = "BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY")
    private BigInteger id;

    @Column(name = "Username", columnDefinition = "VARCHAR(36) NOT NULL UNIQUE")
    private String username;

    @Column(name = "Password",columnDefinition = "CHAR(60) NOT NULL")
    private String password;

    @Column(name = "Enabled", columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE")
    private boolean enabled;

    @Column(name = "Email", columnDefinition = "VARCHAR(254) NOT NULL UNIQUE")
    private String email;

    @Column(name = "FullName", columnDefinition = "VARCHAR(100) NOT NULL")
    private String fullName;

    @Column(name = "PhoneNumber", columnDefinition = "VARCHAR(15) NOT NULL UNIQUE")
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "CreatedAt", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column(name = "LastLogin", columnDefinition = "TIMESTAMP")
    private Timestamp lastLogin;

    @Column(name = "FailedLoginAttempts", columnDefinition = "INT NOT NULL DEFAULT 0")
    private int failedLoginAttempts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Authority> authorities;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserPreferences preferences;

    private void ensureAuthorities() {
        if (this.authorities == null) {
            this.authorities = new HashSet<>();
        }
    }

    public void addAuthority(Authority authority) {
        ensureAuthorities();
        this.authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        ensureAuthorities();
        this.authorities.remove(authority);
    }
}
