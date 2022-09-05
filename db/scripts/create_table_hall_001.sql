CREATE TABLE hall (
    hall_id SERIAL PRIMARY KEY,
    firstName text unique,
    columns int not null,
    rows int not null
);