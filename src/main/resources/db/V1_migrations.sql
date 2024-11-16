CREATE TABLE cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    location VARCHAR(50) NOT NULL CHECK (location IN ('Plovdiv', 'Sofia', 'Varna', 'Burgas')),
    daily_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20),
    age INT,
    has_accidents BOOLEAN
);

CREATE TABLE offers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    car_id INT NOT NULL,
    client_id INT NOT NULL,
    start_date DATE,
    end_date DATE,
    total_price DECIMAL(10, 2),
    accepted BOOLEAN,
    FOREIGN KEY (car_id) REFERENCES cars(id),
    FOREIGN KEY (client_id) REFERENCES clients(id)
);