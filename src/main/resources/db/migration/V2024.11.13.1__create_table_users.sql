CREATE TABLE Users
(
    Id                  BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Username            VARCHAR(36)  NOT NULL UNIQUE,
    Password            CHAR(60)     NOT NULL,
    Enabled             BOOLEAN      NOT NULL DEFAULT TRUE,
    Email               VARCHAR(254) NOT NULL UNIQUE,
    FullName            VARCHAR(100) NOT NULL,
    PhoneNumber         VARCHAR(15)  NOT NULL UNIQUE,

    CreatedAt           TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    LastLogin           TIMESTAMP,

    FailedLoginAttempts INT          NOT NULL DEFAULT 0
);

CREATE INDEX IX_Users_Email ON Users (Email);
CREATE INDEX IX_Users_Username ON Users (Username);
CREATE INDEX IX_Users_PhoneNumber ON Users (PhoneNumber);