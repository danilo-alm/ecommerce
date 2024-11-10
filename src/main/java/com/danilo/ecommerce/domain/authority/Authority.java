package com.danilo.ecommerce.domain.authority;

import com.danilo.ecommerce.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities", uniqueConstraints = {
    @UniqueConstraint(name = "ix_auth_username", columnNames = {"user_id", "authority"})
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@IdClass(AuthorityId.class)
public class Authority implements GrantedAuthority {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT UNSIGNED NOT NULL", foreignKey = @ForeignKey(name = "fk_authorities_users"))
    private User user;

    @Id
    @Column(name = "authority", columnDefinition = "VARCHAR(50) NOT NULL")
    private String authority;
}
