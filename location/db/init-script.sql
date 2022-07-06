DROP DATABASE IF EXISTS location;
CREATE DATABASE location;
USE location;

CREATE TABLE location.city
(
    id                int auto_increment         primary key,
    name              varchar(40)                not null
);

INSERT INTO location.city (id, name) VALUES (1, 'Adana');
INSERT INTO location.city (id, name) VALUES (3, 'Afyon');
INSERT INTO location.city (id, name) VALUES (7, 'Antalya');
