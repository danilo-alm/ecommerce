CREATE TABLE users(
    id SERIAL,
    username VARCHAR(36) NOT NULL UNIQUE,
    password BINARY(60) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    email VARCHAR(254) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,

    currency VARCHAR(3) NOT NULL DEFAULT "USD",
    language VARCHAR(13) NOT NULL DEFAULT "en",

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP,

    failed_login_attempts INT NOT NULL DEFAULT 0
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_username ON users(username);