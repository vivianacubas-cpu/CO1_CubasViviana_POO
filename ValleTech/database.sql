-- ============================================
--  ValleTech - Script de base de datos MySQL
--  Ejecutar en MySQL Workbench o phpMyAdmin
-- ============================================

CREATE DATABASE IF NOT EXISTS dbValleTech
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE dbValleTech;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    usuario  VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Tabla de productos
CREATE TABLE IF NOT EXISTS productos (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    precio    DOUBLE       NOT NULL DEFAULT 0,
    stock     INT          NOT NULL DEFAULT 0
);

-- Usuarios de prueba
INSERT INTO usuarios (usuario, password) VALUES
    ('admin', '1234'),
    ('valletech', 'valle2024');

-- Productos de prueba
INSERT INTO productos (nombre, categoria, precio, stock) VALUES
    ('Laptop HP 15',        'Tecnología',  2500.00, 10),
    ('Mouse Logitech M170', 'Accesorios',    45.00, 50),
    ('Teclado Mecánico',    'Accesorios',   120.00, 30),
    ('Monitor 24" LG',      'Tecnología',   750.00, 15),
    ('Auriculares Sony',    'Audio',        180.00, 25),
    ('Webcam Logitech C920','Accesorios',   250.00, 20),
    ('SSD 1TB Samsung',     'Almacenamiento',320.00, 40),
    ('USB Hub 7 puertos',   'Accesorios',    55.00, 60);
