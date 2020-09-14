-- This file has fixed name and location
DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer (
  accnum INTEGER PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  amount DOUBLE
);