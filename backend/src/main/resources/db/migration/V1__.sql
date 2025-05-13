CREATE TABLE users
(
    id         BIGINT       NOT NULL,
    username   VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    email      VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);