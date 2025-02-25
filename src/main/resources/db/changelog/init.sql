-- liquibase formatted sql

--changeset Dulat:init
CREATE TABLE book
(
    id UUID PRIMARY KEY,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    deleted boolean NOT NULL,

    name text NOT NULL,
    description text,
    year_published integer

);

CREATE TABLE author
(
    id UUID PRIMARY KEY,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    deleted boolean NOT NULL,

    firstname text NOT NULL,
    lastname text,
    middlename text,

    date_of_birth date,
    date_of_death date
);

CREATE TABLE authors_books
(
    author_id UUID NOT NULL,
    book_id UUID NOT NULL

);