CREATE TABLE IF NOT EXISTS patients (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name varchar(10),
    age INT,
    address varchar(100),
    telephone INT,
    gender CHAR(10),
    bloodType CHAR(5),
    diseaseName varchar(50),
    symptoms varchar(20),
    allergy varchar(20),
    PMH varchar(100)
);

CREATE TABLE IF NOT EXISTS bookingLists (
    booking_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    id INTEGER NOT NULL,
    treatmentDetails varchar(20),
    bookingDate DATE,
    bookingTime TIME,
    place varchar(10),

    FOREIGN KEY (id) REFERENCES patients(id)  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users (
    user_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(60) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);