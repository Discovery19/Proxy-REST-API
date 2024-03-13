
CREATE TABLE role (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);
INSERT INTO role (id, name) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'ALBUM'),
(4, 'POST')
;