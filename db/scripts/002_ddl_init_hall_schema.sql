CREATE TABLE hall (
    id SERIAL PRIMARY KEY,
    name text unique,
    columns int not null,
    rows int not null
);