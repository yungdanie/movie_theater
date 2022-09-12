CREATE TABLE ticket (
    ticket_id         SERIAL PRIMARY KEY,
    session_id INT NOT NULL REFERENCES sessions (session_id),
    pos_row    INT NOT NULL,
    cell       INT NOT NULL,
    user_id    INT NOT NULL REFERENCES users (user_id),
    unique (pos_row, cell)
);


