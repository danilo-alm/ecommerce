CREATE TABLE users(
    id SERIAL,
    username VARCHAR(36) NOT NULL PRIMARY KEY,
    password BINARY(60) NOT NULL,
    enabled BOOLEAN NOT NULL,
    email VARCHAR(254) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15),

    currency VARCHAR(3) DEFAULT "USD",
    language VARCHAR(13) DEFAULT "en",

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP,

    failed_login_attempts INT DEFAULT 0
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_username ON users(username);