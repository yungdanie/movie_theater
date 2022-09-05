CREATE TABLE sessions (
    session_id SERIAL PRIMARY KEY,
    firstName text not null,
    description text not null,
    start_time timestamp not null,
    end_time timestamp not null,
    actual boolean,
    tickets_id int[],
    hall_id int references hall (hall_id)
);