CREATE TABLE ticket (
    ticket_id         SERIAL PRIMARY KEY,
    session_id INT NOT NULL REFERENCES sessions (session_id),
    pos_row    INT NOT NULL UNIQUE,
    cell       INT NOT NULL UNIQUE,
    user_id    INT NOT NULL REFERENCES users (user_id)
);
