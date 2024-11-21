CREATE TABLE cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    location VARCHAR(50) NOT NULL CHECK (location IN ('Plovdiv', 'Sofia', 'Varna', 'Burgas')),
    daily_price DECIMAL(10, 2) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE CLIENTS (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     location VARCHAR(50) NOT NULL CHECK (location IN ('Plovdiv', 'Sofia', 'Varna', 'Burgas')),
     phone_number VARCHAR(20) NOT NULL,
     age INT NOT NULL,
     has_accidents BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE offers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    car_id INT NOT NULL,
    client_id INT NOT NULL,
    week_days_count INT NOT NULL,
    weekend_days_count INT NOT NULL,
    total_price DECIMAL(10, 2),
    is_accepted BOOLEAN NOT NULL DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (car_id) REFERENCES cars(id),
    FOREIGN KEY (client_id) REFERENCES clients(id)
);