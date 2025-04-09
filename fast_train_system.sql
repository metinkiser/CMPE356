-- 1) Mevcut veritabanını sil ve yeniden oluştur
DROP DATABASE IF EXISTS fast_train_system;
CREATE DATABASE fast_train_system;
USE fast_train_system;

-- 2) Tabloları varsa önce sil
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS journeys;
DROP TABLE IF EXISTS trains;
DROP TABLE IF EXISTS stations;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

-- 3) roles tablosu (Kullanıcı rolleri)
CREATE TABLE roles (
  role_id INT AUTO_INCREMENT PRIMARY KEY,
  role_name VARCHAR(20) NOT NULL
);

-- 4) users tablosu (Tüm kullanıcılar)
CREATE TABLE users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role_id INT NOT NULL,
  is_approved BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (role_id) REFERENCES roles(role_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);

-- 5) employees tablosu (manager'ın eklediği çalışanlar, opsiyonel)
CREATE TABLE employees (
  employee_id INT AUTO_INCREMENT PRIMARY KEY,
  added_by INT, -- hangi user_id eklediyse
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  age INT,
  gender ENUM('F','M','Other') DEFAULT 'Other',
  phone VARCHAR(20),
  address TEXT,
  FOREIGN KEY (added_by) REFERENCES users(user_id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

-- 6) stations tablosu (Tren istasyonları)
CREATE TABLE stations (
  station_id INT AUTO_INCREMENT PRIMARY KEY,
  station_name VARCHAR(100) NOT NULL
);

-- 7) trains tablosu (Tek tip tren, ama isterseniz farklı isimler koyabilirsiniz)
CREATE TABLE trains (
  train_id INT AUTO_INCREMENT PRIMARY KEY,
  train_name VARCHAR(100) NOT NULL
  -- Burada tren türü ya da kapasite vs. yok
);

-- 8) journeys tablosu (Sefer bilgileri: kalkış/varış istasyonu, tarih-saat, fiyat)
CREATE TABLE journeys (
  journey_id INT AUTO_INCREMENT PRIMARY KEY,
  train_id INT NOT NULL,
  departure_station INT NOT NULL,
  arrival_station INT NOT NULL,
  departure_date DATE NOT NULL,
  departure_time TIME NOT NULL,
  arrival_date DATE NOT NULL,
  arrival_time TIME NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (train_id) REFERENCES trains(train_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (departure_station) REFERENCES stations(station_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  FOREIGN KEY (arrival_station) REFERENCES stations(station_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);

-- 9) seats tablosu (Her seferin koltukları)
CREATE TABLE seats (
  seat_id INT AUTO_INCREMENT PRIMARY KEY,
  journey_id INT NOT NULL,
  seat_number VARCHAR(5) NOT NULL,  -- Örnek: A1, A2...
  is_reserved BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (journey_id) REFERENCES journeys(journey_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- 10) tickets tablosu (Hangi kullanıcı, hangi seferde, hangi koltuğu aldı?)
CREATE TABLE tickets (
  ticket_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  seat_id INT NOT NULL,
  journey_id INT NOT NULL,
  purchase_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (seat_id) REFERENCES seats(seat_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (journey_id) REFERENCES journeys(journey_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- 11) payments tablosu (Ödeme kayıtları)
CREATE TABLE payments (
  payment_id INT AUTO_INCREMENT PRIMARY KEY,
  ticket_id INT NOT NULL,
  cardholder_name VARCHAR(100) NOT NULL,
  card_number_last4 CHAR(4) NOT NULL, -- Sadece son 4 hane
  amount DECIMAL(10,2) NOT NULL,
  payment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- 12) Rollere 5 örnek veri
INSERT INTO roles (role_name) VALUES
('admin'),
('user'),
('manager'),
('guest'),
('tester');

-- 13) users tablosuna 5 örnek veri
INSERT INTO users (first_name, last_name, email, password_hash, role_id, is_approved)
VALUES
('Ali','Veli','ali@example.com','hash123',1,TRUE),       -- Admin
('Ayşe','Demir','ayse@example.com','hash456',2,TRUE),   -- User
('Manager','Bey','manager@example.com','hash789',3,TRUE), -- Manager
('Fatma','Kaya','fatma@example.com','hashabc',2,FALSE),   -- User, onay bekliyor
('Mehmet','Yilmaz','mehmet@example.com','hashxyz',4,TRUE);

-- 14) employees tablosuna 5 örnek veri (manager = user_id=3)
INSERT INTO employees (added_by, name, surname, age, gender, phone, address)
VALUES
(3, 'Emre', 'Polat', 30, 'M', '05321234567', 'Istanbul'),
(3, 'Ahmet', 'Yildiz', 27, 'M', '05429876543', 'Ankara'),
(3, 'Zeynep', 'Acar', 25, 'F', '05051231212', 'Konya'),
(3, 'Seda', 'Erkan', 29, 'F', '05071112233', 'Eskisehir'),
(3, 'Mustafa', 'Guven', 35, 'M', '05338889900', 'Bursa');

-- 15) stations tablosuna 5 örnek veri
INSERT INTO stations (station_name)
VALUES
('Istanbul'),
('Ankara'),
('Konya'),
('Eskisehir'),
('Bursa');

-- 16) trains tablosuna 5 örnek veri (hepsi aynı tip tren olabilir, sadece isim farklı)
INSERT INTO trains (train_name)
VALUES
('YHT Istanbul-Ankara #1'),
('YHT Istanbul-Ankara #2'),
('YHT Ankara-Konya'),
('YHT Eskisehir-Ankara'),
('YHT Bursa-Istanbul');

-- 17) journeys tablosuna 5 örnek veri
-- stations: 1=Istanbul,2=Ankara,3=Konya,4=Eskisehir,5=Bursa
-- trains: 1..5
INSERT INTO journeys (
  train_id,
  departure_station, arrival_station,
  departure_date, departure_time,
  arrival_date, arrival_time,
  price
)
VALUES
(1, 1, 2, '2025-04-10', '08:00:00', '2025-04-10', '11:30:00', 150.00), -- YHT IST->ANK
(1, 1, 2, '2025-04-11', '13:00:00', '2025-04-11', '16:30:00', 150.00), -- YHT IST->ANK (başka sefer)
(3, 2, 3, '2025-04-11', '09:15:00', '2025-04-11', '11:00:00', 120.50), -- YHT ANK->KON
(4, 4, 2, '2025-04-12', '14:00:00', '2025-04-12', '17:15:00', 90.00),  -- YHT ESK->ANK
(5, 5, 1, '2025-04-12', '07:00:00', '2025-04-12', '10:30:00', 100.00); -- YHT BUR->IST

-- 18) seats tablosuna 5 örnek veri
-- journey_id => 1..5
INSERT INTO seats (journey_id, seat_number, is_reserved)
VALUES
(1, 'A1', FALSE),
(1, 'A2', FALSE),
(2, 'B1', FALSE),
(3, 'C3', FALSE),
(5, 'D5', FALSE);

-- 19) tickets tablosuna 5 örnek veri
-- user_id => 1..5, seat_id => 1..5, journey_id => seats ile uyumlu
INSERT INTO tickets (user_id, seat_id, journey_id)
VALUES
(2, 1, 1),  -- Ayşe seat1 sefer1
(4, 2, 1),  -- Fatma seat2 sefer1
(5, 3, 2),  -- Mehmet seat3 sefer2
(2, 4, 3),  -- Ayşe seat4 sefer3
(3, 5, 5);  -- Manager seat5 sefer5

-- 20) payments tablosuna 5 örnek veri
-- ticket_id => 1..5
INSERT INTO payments (ticket_id, cardholder_name, card_number_last4, amount)
VALUES
(1, 'Ayse Demir', '1234', 150.00),
(2, 'Fatma Kaya', '4321', 150.00),
(3, 'Mehmet Yilmaz', '9876', 120.50),
(4, 'Ayse Demir', '1111', 90.00),
(5, 'Manager Bey', '2222', 100.00);