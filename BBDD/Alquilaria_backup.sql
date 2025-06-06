-- ------- Proyecto formacion de empresa ----------

-- Alejandro Guaman Zuñiga

-- Creacion de la base de datos Alquilaria

DROP DATABASE IF EXISTS alquilaria;
CREATE DATABASE alquilaria;

USE alquilaria;

DROP TABLE IF EXISTS propietario;
CREATE TABLE propietario (
	id INT AUTO_INCREMENT PRIMARY KEY,
    DNI VARCHAR(15) UNIQUE NOT NULL,
    nombre VARCHAR(50),
    email VARCHAR(100),
    telefono VARCHAR(15)
);

DROP TABLE IF EXISTS vivienda;
CREATE TABLE vivienda (
	codigo INT PRIMARY KEY,
    id_propietario INT,
    superficie VARCHAR(20),
    descripcion VARCHAR(100),
    mascotas BOOL,
    precio_mensual DECIMAL(10, 2),
    direccion VARCHAR(100),
	FOREIGN KEY (id_propietario) REFERENCES propietario(id) 
);

DROP TABLE IF EXISTS inquilino;
CREATE TABLE inquilino (
	id INT AUTO_INCREMENT PRIMARY KEY,
    DNI VARCHAR(15) UNIQUE,
    nombre VARCHAR(50),
    email VARCHAR(100),
    telefono VARCHAR(15),
    mascota BOOL
);

DROP TABLE IF EXISTS contrata;
CREATE TABLE contrata (
	id_inquilino INT,
    codigo_vivienda INT,
    precio DECIMAL(10, 2),
    estado ENUM('activo', 'vencido', 'pendiente') DEFAULT 'pendiente',
    fecha_inicio DATE,
    fecha_fin DATE,
    PRIMARY KEY (id_inquilino, codigo_vivienda),
    FOREIGN KEY (id_inquilino) REFERENCES inquilino(id),
    FOREIGN KEY (codigo_vivienda) REFERENCES vivienda(codigo)
);

DROP TABLE IF EXISTS tipo;
CREATE TABLE tipo (
	codigo_vivienda INT,
    tipo_vivienda VARCHAR(50),
    PRIMARY KEY (codigo_vivienda, tipo_vivienda),
    FOREIGN KEY (codigo_vivienda) REFERENCES vivienda(codigo)
);

-- Trigger para saber si esta acitvo, pendiente o vencio el contrato

DELIMITER //
DROP TRIGGER IF EXISTS tr_contrata_before_insert//
CREATE TRIGGER tr_contrata_before_insert BEFORE INSERT
ON contrata
FOR EACH ROW
BEGIN
	
    IF NEW.fecha_fin <= NOW() THEN
		SET NEW.estado = 'vencido';
        
	ELSEIF NEW.fecha_inicio <= NOW() THEN
		SET NEW.estado = 'activo';
	
    ELSE 
		SET NEW.estado = 'pendiente';
        
	END IF;
    
END//

DELIMITER ;

DELIMITER //
DROP TRIGGER IF EXISTS tr_contrata_before_update//
CREATE TRIGGER tr_contrata_before_update BEFORE UPDATE
ON contrata
FOR EACH ROW
BEGIN
	
    IF NEW.fecha_fin <= NOW() THEN
		SET NEW.estado = 'vencido';
        
	ELSEIF NEW.fecha_inicio <= NOW() THEN
		SET NEW.estado = 'activo';
        
	ELSE 
		SET NEW.estado = 'pendiente';
        
	END IF;
    
END//

DELIMITER ;