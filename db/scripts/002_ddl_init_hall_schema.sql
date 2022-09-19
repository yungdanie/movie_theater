CREATE TABLE hall (
    id SERIAL PRIMARY KEY,
    name text unique,
    columns_count int not null,
    rows_count int not null
);