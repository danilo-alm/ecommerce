package com.danilo.ecommerce.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.ZoneId;
import java.util.Currency;
import java.util.Locale;

@Entity
@Table(name = "UserPreferences")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class UserPreferences {

    @Id
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserId", columnDefinition = "BIGINT UNSIGNED NOT NULL", foreignKey = @ForeignKey(name = "FK_UserPreferences_Users"))
    private User user;

    @Column(name = "Currency", columnDefinition = "VARCHAR(3) NOT NULL DEFAULT 'USD'")
    private Currency currency;

    @Column(name = "Locale", columnDefinition = "VARCHAR(13) NOT NULL DEFAULT 'en'")
    private Locale locale;

    @Column(name = "TimeZone", columnDefinition = "VARCHAR(40) NOT NULL DEFAULT 'GMT-5'")
    private ZoneId timeZone;
}
