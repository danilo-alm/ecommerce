CREATE TABLE authorities(
    user_id BIGINT UNSIGNED NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities(user_id,authority);
