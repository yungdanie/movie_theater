CREATE TABLE sessions (
    id SERIAL PRIMARY KEY,
    name text not null,
    description text not null,
    start_time timestamp not null,
    end_time timestamp not null,
    actual boolean,
    hall_id int references hall (id)
);
