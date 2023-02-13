CREATE TABLE cars
(
    id    REAL PRIMARY KEY,
    brand VARCHAR,
    model VARCHAR,
    price NUMERIC(10, 2)
);

CREATE TABLE humans
(
    name    VARCHAR PRIMARY KEY,
    age     SMALLSERIAL,
    license BOOLEAN,
    car_id  REAL REFERENCES cars
);