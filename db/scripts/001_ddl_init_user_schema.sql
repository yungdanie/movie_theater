CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    firstName VARCHAR NOT NULL,
    surName VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    phone VARCHAR NOT NULL,
    unique (firstName, surName, email, phone)
);
