drop database car_dealership;

CREATE SCHEMA IF NOT EXISTS car_dealership;

-- Dealerships
CREATE TABLE IF NOT EXISTS car_dealership.dealerships
(
    dealership_id   INT AUTO_INCREMENT PRIMARY KEY,
    dealership_name VARCHAR(255) NOT NULL,
    address         VARCHAR(255) NOT NULL,
    phone           VARCHAR(15)  NOT NULL,
    UNIQUE (dealership_name)
);

-- Vehicles
CREATE TABLE IF NOT EXISTS car_dealership.vehicle
(
    vin      VARCHAR(17)                          NOT NULL PRIMARY KEY,
    year     INT                                  NOT NULL,
    make     VARCHAR(255)                         NOT NULL,
    model    VARCHAR(255)                         NOT NULL,
    type     VARCHAR(255),
    color    VARCHAR(100)                         NOT NULL,
    price    NUMERIC(10, 2)                       NOT NULL,
    odometer DOUBLE PRECISION                     NOT NULL,
    sold     ENUM ('available', 'sold', 'leased') NOT NULL DEFAULT 'available'
);

-- Inventory
CREATE TABLE IF NOT EXISTS car_dealership.inventory
(
    vin           VARCHAR(17) PRIMARY KEY,
    dealership_id INT,

    FOREIGN KEY (dealership_id) REFERENCES car_dealership.dealerships (dealership_id),
    FOREIGN KEY (vin) REFERENCES car_dealership.vehicle (vin)
);