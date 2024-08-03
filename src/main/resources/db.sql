DROP TABLE IF EXISTS Sensor;
DROP TABLE IF EXISTS Measurement;

CREATE TABLE Sensor
(
    id   INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE Measurement
(
    id                  INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    temperature         DOUBLE PRECISION NOT NULL,
    is_rainy            BOOLEAN          NOT NULL,
    time_of_measurement TIMESTAMP,
    sensor              VARCHAR(30) REFERENCES Sensor (name) ON DELETE CASCADE
);

INSERT INTO Sensor(name)
VALUES ('Sensor_1'),
       ('Sensor_2'),
       ('Sensor_3');