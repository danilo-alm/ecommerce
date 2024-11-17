CREATE TABLE UserPreferences
(
    UserId   BIGINT UNSIGNED NOT NULL,
    Currency VARCHAR(3)      NOT NULL DEFAULT 'USD',
    Locale   VARCHAR(13)     NOT NULL DEFAULT 'en-us',
    TimeZone VARCHAR(40)     NOT NULL DEFAULT 'GMT-5',
    CONSTRAINT FK_UserPreferences_Users FOREIGN KEY (UserId) REFERENCES Users (Id)
);
