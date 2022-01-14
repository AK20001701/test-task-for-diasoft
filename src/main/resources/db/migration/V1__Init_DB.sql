CREATE TABLE tUser
(
    id                bigserial PRIMARY KEY,
    email             text      NOT NULL UNIQUE,
    phone_number      text      NOT NULL UNIQUE,
    password          text      NOT NULL,
    first_name        text      NOT NULL,
    last_name         text      NOT NULL,
    registration_date timestamp NOT NULL
);