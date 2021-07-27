CREATE TABLE BOOK
(
    id    serial PRIMARY KEY,
    title VARCHAR(200) UNIQUE NOT NULL
);

INSERT INTO BOOK(title)
VALUES ('The Little Prince');