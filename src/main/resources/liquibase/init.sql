CREATE TABLE dogs
(
    id            BIGINT generated by default as identity primary key,
    name          TEXT NOT NULL,
    breed         TEXT NOT NULL,
    date_of_birth DATE,
    suit          TEXT NOT NULL,
    gender        TEXT NOT NULL,
    owner_id BIGINT
);

CREATE TABLE cats
(
    id            BIGINT generated by default as identity primary key,
    name          TEXT NOT NULL,
    breed         TEXT NOT NULL,
    date_of_birth DATE,
    suit          TEXT NOT NULL,
    gender        TEXT NOT NULL,
    owner_id BIGINT
);

CREATE TABLE reports
(
    id      BIGINT generated by default as identity primary key,
    date    timestamp NOT NULL,
    photo   bytea     NOT NULL,
    report  TEXT      NOT NULL,
    chat_id BIGINT
);

CREATE TABLE clients
(
    id           BIGINT generated by default as identity primary key,
    first_name   TEXT    NOT NULL,
    last_name    TEXT    NOT NULL,
    phone_number TEXT    NOT NULL,
    chat_id BIGINT
);

CREATE TABLE cat_owners
(
    owner_id     BIGINT generated by default as identity primary key,
    first_name   TEXT,
    last_name    TEXT,
    phone_number TEXT,
    probation    timestamp,
    chat_id      BIGINT
);

CREATE TABLE dog_owners
(
    owner_id     BIGINT generated by default as identity primary key,
    first_name   TEXT,
    last_name    TEXT,
    phone_number TEXT,
    probation    timestamp,
    chat_id      BIGINT
);

CREATE TABLE volunteers
(
    id           BIGINT generated by default as identity primary key,
    first_name   TEXT NOT NULL,
    last_name    TEXT,
    phone_number TEXT NOT NULL,
    chat_id      BIGINT
);