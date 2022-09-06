CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    firstName VARCHAR NOT NULL,
    surName VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    phone VARCHAR NOT NULL UNIQUE,
    unique (firstName, surName, email, phone)
);
