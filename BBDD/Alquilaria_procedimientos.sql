

-- Consultas de la tabla propietario



-- Insertar 

DELIMITER //
DROP PROCEDURE IF EXISTS sp_insert_propietario//
CREATE PROCEDURE sp_insert_propietario (
	IN DNI VARCHAR(15),
    IN nombre VARCHAR(50),
    IN email VARCHAR(100),
    IN telefono VARCHAR(15),
    OUT id INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET id = -1;
    
    SET @declaracion_stmt = 'INSERT INTO propietario(DNI, nombre, email, telefono) VALUES(?, ?, ?, ?)';
    
    PREPARE prepared_stmt FROM @declaracion_stmt;
    
    SET @DNI = DNI;
    SET @nombre = nombre;
    SET @email = email;
    SET @telefono = telefono;
    EXECUTE prepared_stmt
    USING @DNI, @nombre, @email, @telefono;
    
    SET id = LAST_INSERT_ID();
    
    DEALLOCATE PREPARE prepared_stmt;
	
END//
DELIMITER ;



-- Select


DELIMITER //

DROP PROCEDURE IF EXISTS sp_select_propietario//
CREATE PROCEDURE sp_select_propietario (
    IN p_id INT,
    OUT o_DNI VARCHAR(15),
    OUT o_nombre VARCHAR(50),
    OUT o_email VARCHAR(100),
    OUT o_telefono VARCHAR(15),
    OUT hecho INT
)
BEGIN
    DECLARE v_count INT DEFAULT 0;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
        SET hecho = -1;

    SELECT COUNT(*) INTO v_count
    FROM propietario
    WHERE id = p_id;

    IF v_count = 1 THEN
        SELECT DNI, nombre, email, telefono
        INTO o_DNI, o_nombre, o_email, o_telefono
        FROM propietario
        WHERE id = p_id
        LIMIT 1;

        SET hecho = 1;
    ELSE
        SET hecho = 0;
        SET o_DNI = NULL;
        SET o_nombre = NULL;
        SET o_email = NULL;
        SET o_telefono = NULL;

    END IF;

END//
DELIMITER ;


-- delete


DELIMITER //
DROP PROCEDURE IF EXISTS sp_delete_propietario//
CREATE PROCEDURE sp_delete_propietario (
	IN p_id INT,
    OUT hecho INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    SET @declaracion_stmt = 'DELETE FROM propietario WHERE id = ?';
    
    PREPARE prepared_stmt FROM @declaracion_stmt;
    
    SET @p_id = p_id;
    EXECUTE prepared_stmt
    USING @p_id;
    
    SET hecho = ROW_COUNT();
    
    DEALLOCATE PREPARE prepared_stmt;
	
END//
DELIMITER ;



-- modificar

DELIMITER //

DROP PROCEDURE IF EXISTS sp_update_propietario//
CREATE PROCEDURE sp_update_propietario (
    IN p_id INT,
    IN modificacion VARCHAR(20),
    IN dato VARCHAR(100),
    OUT hecho INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;

    SET @declaracion_stmt = CONCAT('UPDATE propietario SET ', modificacion, ' = ? WHERE id = ?');

    PREPARE prepared_stmt FROM @declaracion_stmt;
    SET @dato = dato;
    SET @p_id = p_id;
    EXECUTE prepared_stmt 
    USING @dato, @p_id;

    SET hecho = ROW_COUNT();

    DEALLOCATE PREPARE prepared_stmt;
END//

DELIMITER ;


-- ---------------------------------------------------------------------------------------------------------


-- Consultas de la tabla Inquilino


-- Insertar

DELIMITER //
DROP PROCEDURE IF EXISTS sp_insert_inquilino//
CREATE PROCEDURE sp_insert_inquilino (
	IN DNI VARCHAR(15),
    IN nombre VARCHAR(50),
    IN email VARCHAR(100),
    IN telefono VARCHAR(15),
    IN mascota INT,
    OUT id INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET id = -1;
    
    SET @declaracion_stmt = 'INSERT INTO inquilino(DNI, nombre, email, telefono, mascota) VALUES(?, ?, ?, ?, ?)';
    
    PREPARE prepared_stmt FROM @declaracion_stmt;
    
    SET @DNI = DNI;
    SET @nombre = nombre;
    SET @email = email;
    SET @telefono = telefono;
    SET @mascota = mascota;
    EXECUTE prepared_stmt
    USING @DNI, @nombre, @email, @telefono, @mascota;
    
    SET id = LAST_INSERT_ID();
    
    DEALLOCATE PREPARE prepared_stmt;
	
END//
DELIMITER ;


-- select 

DELIMITER //
DROP PROCEDURE IF EXISTS sp_select_inquilino//
CREATE PROCEDURE sp_select_inquilino (
    IN p_id INT,
    OUT o_DNI VARCHAR(15),
    OUT o_nombre VARCHAR(50),
    OUT o_email VARCHAR(100),
    OUT o_telefono VARCHAR(15),
    OUT o_mascota INT,
    OUT hecho INT
)
BEGIN
    DECLARE v_count INT DEFAULT 0;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
        SET hecho = -1;

    SELECT COUNT(*) INTO v_count
    FROM inquilino
    WHERE id = p_id;

    IF v_count = 1 THEN
        SELECT DNI, nombre, email, telefono, mascota
        INTO o_DNI, o_nombre, o_email, o_telefono, o_mascota
        FROM inquilino
        WHERE id = p_id
        LIMIT 1;

        SET hecho = 1;

    ELSE
        SET hecho = 0;
        SET o_DNI = NULL;
        SET o_nombre = NULL;
        SET o_email = NULL;
        SET o_telefono = NULL;
        SET o_mascota = NULL;

    END IF;

END//
DELIMITER ;


-- delete


DELIMITER //
DROP PROCEDURE IF EXISTS sp_delete_inquilino//
CREATE PROCEDURE sp_delete_inquilino (
	IN p_id INT,
    OUT hecho INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    SET @declaracion_stmt = 'DELETE FROM inquilino WHERE id = ?';
    
    PREPARE prepared_stmt FROM @declaracion_stmt;
    
    SET @p_id = p_id;
    EXECUTE prepared_stmt
    USING @p_id;
    
    SET hecho = ROW_COUNT();
    
    DEALLOCATE PREPARE prepared_stmt;
	
END//
DELIMITER ;


-- update

DELIMITER //
DROP PROCEDURE IF EXISTS sp_update_inquilino//
CREATE PROCEDURE sp_update_inquilino (
	IN p_id INT,
    IN modificacion VARCHAR(20),
    IN dato VARCHAR(100),
    OUT hecho INT
)
BEGIN
	
    DECLARE datoN INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    IF dato = '1' OR dato = '0'THEN
    
		IF dato = '1' THEN
			SET datoN = 1;
		ELSE 
			SET datoN = 0;
        END IF;
        
		SET @declaracion_stmt = CONCAT('UPDATE inquilino SET ', modificacion, ' = ? WHERE id = ?');
    
		PREPARE prepared_stmt FROM @declaracion_stmt;
    
		SET @p_id = p_id;
		SET @datoN = datoN;
		EXECUTE prepared_stmt
		USING @datoN, @p_id;
    
		SET hecho = ROW_COUNT();
    
		DEALLOCATE PREPARE prepared_stmt;
        
    ELSE
        SET @declaracion_stmt = CONCAT('UPDATE inquilino SET ', modificacion, ' = ? WHERE id = ?');
    
		PREPARE prepared_stmt FROM @declaracion_stmt;
    
		SET @p_id = p_id;
		SET @dato = dato;
		EXECUTE prepared_stmt
		USING @dato, @p_id;
    
		SET hecho = ROW_COUNT();
    
		DEALLOCATE PREPARE prepared_stmt;
    
    END IF;
	
END//
DELIMITER ;



-- -------------------------------------------------------------------------------------------------


-- Consultas de la tabla vivienda

-- Insertar

DELIMITER //
DROP PROCEDURE IF EXISTS sp_insert_vivienda//
CREATE PROCEDURE sp_insert_vivienda (
	IN codigo INT,
    IN id_propietario INT,
    IN superficie VARCHAR(20),
    IN descripcion VARCHAR(100),
    IN mascotas INT,
    IN precio_mensual DECIMAL(10,2),
    IN direccion VARCHAR(100),
    OUT hecho INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    SET @declaracion_stmt = 'INSERT INTO vivienda(codigo, id_propietario, superficie, descripcion, mascotas, precio_mensual, direccion)
		VALUES (?, ?, ?, ?, ?, ?, ?)';
    
    PREPARE prepared_stmt FROM @declaracion_stmt;
    
    SET @codigo = codigo;
    SET @id_propietario = id_propietario;
    SET @superficie = superficie;
    SET @descripcion = descripcion;
    SET @mascotas = mascotas;
    SET @precio_mensual = precio_mensual;
    SET @direccion = direccion;
    EXECUTE prepared_stmt
    USING @codigo, @id_propietario, @superficie, @descripcion, @mascotas, @precio_mensual, @direccion;
    
    SET hecho = ROW_COUNT();
    
    DEALLOCATE PREPARE prepared_stmt;
	
END//
DELIMITER ;



-- select


DELIMITER //
DROP PROCEDURE IF EXISTS sp_select_vivienda//
CREATE PROCEDURE sp_select_vivienda (
    IN p_codigo INT,
    OUT o_id_propietario  INT,
    OUT o_superficie VARCHAR(20),
    OUT o_descripcion VARCHAR(100),
    OUT o_mascotas INT,
    OUT o_precio_mensual DECIMAL(10, 2),
    OUT o_direccion VARCHAR(100),
    OUT hecho INT
)
BEGIN
    DECLARE v_count INT DEFAULT 0;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
        SET hecho = -1;

    SELECT COUNT(*) INTO v_count
    FROM vivienda
    WHERE codigo = p_codigo;

    IF v_count = 1 THEN
        SELECT id_propietario, superficie, descripcion, mascotas, precio_mensual, direccion
        INTO o_id_propietario, o_superficie, o_descripcion, o_mascotas, o_precio_mensual, o_direccion
        FROM vivienda
        WHERE codigo = p_codigo
        LIMIT 1;

        SET hecho = 1;

    ELSE
        SET hecho = 0;
        SET o_id_propietario = NULL;
        SET o_superficie = NULL;
        SET o_descripcion = NULL;
        SET o_mascotas = NULL;
        SET o_precio_mensual = NULL;
        SET o_direccion = NULL;

    END IF;

END//
DELIMITER ;



-- delete

DELIMITER //
DROP PROCEDURE IF EXISTS sp_delete_vivienda//
CREATE PROCEDURE sp_delete_vivienda (
	IN p_codigo INT,
    OUT hecho INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    SET @declaracion_stmt = 'DELETE FROM vivienda WHERE codigo = ?';
    
    PREPARE prepared_stmt FROM @declaracion_stmt;
    
    SET @p_codigo = p_codigo;
    EXECUTE prepared_stmt
    USING @p_codigo;
    
    SET hecho = ROW_COUNT();
    
    DEALLOCATE PREPARE prepared_stmt;
	
END//
DELIMITER ;


-- update

DELIMITER //
DROP PROCEDURE IF EXISTS sp_update_vivienda//
CREATE PROCEDURE sp_update_vivienda (
	IN p_codigo INT,
    IN modificacion VARCHAR(20),
    IN dato VARCHAR(100),
    OUT hecho INT
)
BEGIN
	
    DECLARE datoN DECIMAL(10, 2);
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    IF modificacion = 'precio_mensual' THEN
    
		SET datoN = CAST(dato AS DOUBLE);
        
        SET @declaracion_stmt = CONCAT('UPDATE vivienda SET ', modificacion, ' = ? WHERE codigo = ?');
    
		PREPARE prepared_stmt FROM @declaracion_stmt;
    
		SET @p_codigo = p_codigo;
		SET @datoN = datoN;
		EXECUTE prepared_stmt
		USING @datoN, @p_codigo;
    
		SET hecho = ROW_COUNT();
    
		DEALLOCATE PREPARE prepared_stmt;
    
    ELSEIF dato = '1' OR dato = '0' THEN
		
        IF dato = '1' THEN
			SET datoN = 1;
		ELSE
			SET datoN = 0;
		END IF;
        
		SET @declaracion_stmt = CONCAT('UPDATE vivienda SET ', modificacion, ' = ? WHERE codigo = ?');
    
		PREPARE prepared_stmt FROM @declaracion_stmt;
    
		SET @p_codigo = p_codigo;
		SET @datoN = datoN;
		EXECUTE prepared_stmt
		USING @datoN, @p_codigo;
    
		SET hecho = ROW_COUNT();
    
		DEALLOCATE PREPARE prepared_stmt;
        
    ELSE
		SET @declaracion_stmt = CONCAT('UPDATE vivienda SET ', modificacion, ' = ? WHERE codigo = ?');
    
		PREPARE prepared_stmt FROM @declaracion_stmt;
    
		SET @p_codigo = p_codigo;
		SET @dato = dato;
		EXECUTE prepared_stmt
		USING @dato, @p_codigo;
    
		SET hecho = ROW_COUNT();
    
		DEALLOCATE PREPARE prepared_stmt;
        
    END IF;
	
END//
DELIMITER ;


-- --------------------------------------------------------------------------------------------------------------------


-- Consultas de la tabla Tipo


-- Insertar


DELIMITER //
DROP PROCEDURE IF EXISTS sp_insert_tipo//
CREATE PROCEDURE sp_insert_tipo (
	IN codigo_vivienda INT,
    IN tipo_vivienda VARCHAR(50),
    OUT hecho INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    SET @declaracion_stmt = 'INSERT INTO tipo(codigo_vivienda, tipo_vivienda) VALUES(?, ?)';
    
    PREPARE prepared_stmt FROM @declaracion_stmt;
    
    SET @codigo_vivienda = codigo_vivienda;
    SET @tipo_vivienda = tipo_vivienda;
    EXECUTE prepared_stmt
    USING @codigo_vivienda, @tipo_vivienda;
    
    SET hecho = ROW_COUNT();
    
    DEALLOCATE PREPARE prepared_stmt;
	
END//
DELIMITER ;



-- select


DELIMITER //
DROP PROCEDURE IF EXISTS sp_select_tipo//
CREATE PROCEDURE sp_select_tipo (
	IN p_codigo_vivienda INT,
    OUT o_tipo_vivienda VARCHAR(50),
    OUT hecho INT
)
BEGIN
	
    DECLARE v_count INT DEFAULT 0;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
        SET hecho = -1;

    SELECT COUNT(*) INTO v_count
    FROM tipo
    WHERE codigo_vivienda = p_codigo_vivienda;

    IF v_count = 1 THEN
        SELECT tipo_vivienda INTO o_tipo_vivienda
        FROM tipo
        WHERE codigo_vivienda = p_codigo_vivienda
        LIMIT 1;

        SET hecho = 1;

    ELSE
        SET hecho = 0;
        SET o_tipo_vivienda = NULL;

    END IF;
	
END//
DELIMITER ;



-- delete


DELIMITER //
DROP PROCEDURE IF EXISTS sp_delete_tipo//
CREATE PROCEDURE sp_delete_tipo (
	IN p_codigo_vivienda INT,
    OUT hecho INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    SET @declaracion_stmt = 'DELETE FROM tipo WHERE codigo_vivienda = ?';
    
    PREPARE prepared_stmt FROM @declaracion_stmt;
    
    SET @p_codigo_vivienda = p_codigo_vivienda;
    EXECUTE prepared_stmt
    USING @p_codigo_vivienda;
    
    SET hecho = ROW_COUNT();
    
    DEALLOCATE PREPARE prepared_stmt;
	
END//
DELIMITER ;



-- update


DELIMITER //
DROP PROCEDURE IF EXISTS sp_update_tipo//
CREATE PROCEDURE sp_update_tipo (
	IN p_codigo_vivienda INT,
    IN dato VARCHAR(100),
    OUT hecho INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;

    UPDATE tipo
    SET tipo_vivienda = dato
    WHERE codigo_vivienda = p_codigo_vivienda;

    SET hecho = ROW_COUNT();
	
END//
DELIMITER ;


--  ----------------------------------------------------------------------------------------------------------------------


-- Consultas de la tabla contrata


-- Insertar


DELIMITER //
DROP PROCEDURE IF EXISTS sp_insert_contrata//
CREATE PROCEDURE sp_insert_contrata (
	IN id_inquilino INT,
    IN codigo_vivienda INT,
    IN precio DECIMAL(10, 2),
    IN fecha_inicio DATE,
    IN fecha_fin DATE,
    OUT hecho INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    SET @declaracion_stmt = 'INSERT INTO contrata(id_inquilino, codigo_vivienda, precio, fecha_inicio, fecha_fin)
	VALUES(?, ?, ?, ?, ?)';
    
    PREPARE prepared_stmt FROM @declaracion_stmt;
    
    SET @id_inquilino = id_inquilino;
    SET @codigo_vivienda = codigo_vivienda;
    SET @precio = precio;
    SET @fecha_inicio = fecha_inicio;
    SET @fecha_fin = fecha_fin;
    EXECUTE prepared_stmt
    USING @id_inquilino, @codigo_vivienda, @precio, @fecha_inicio, @fecha_fin;
    
    SET hecho = ROW_COUNT();
    
    DEALLOCATE PREPARE prepared_stmt;
	
END//
DELIMITER ;



-- select 

DELIMITER //
DROP PROCEDURE IF EXISTS sp_select_contrata//
CREATE PROCEDURE sp_select_contrata (
	IN p_id_inquilino INT,
    IN p_codigo_vivienda INT,
    OUT o_precio DECIMAL(10, 2),
    OUT o_estado VARCHAR(20),
    OUT o_fecha_inicio DATE,
    OUT o_fecha_fin DATE,
    OUT hecho INT
)
BEGIN
	
    DECLARE v_count INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;

    SELECT COUNT(*) INTO v_count
    FROM contrata
    WHERE id_inquilino = p_id_inquilino AND codigo_vivienda = p_codigo_vivienda;

    IF v_count = 1 THEN
        SELECT precio, estado, fecha_inicio, fecha_fin
        INTO o_precio, o_estado, o_fecha_inicio, o_fecha_fin
        FROM contrata
        WHERE id_inquilino = p_id_inquilino AND codigo_vivienda = p_codigo_vivienda
        LIMIT 1;

        SET hecho = 1;
    ELSE

        SET hecho = 0;
        SET o_precio = NULL;
        SET o_estado = NULL;
        SET o_fecha_inicio = NULL;
        SET o_fecha_fin = NULL;

    END IF;
	
END//
DELIMITER ;



-- delete

DELIMITER //
DROP PROCEDURE IF EXISTS sp_delete_contrata//
CREATE PROCEDURE sp_delete_contrata (
	IN p_id_inquilino INT,
	IN p_codigo_vivienda INT,
    OUT hecho INT
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    SET @declaracion_stmt = 'DELETE FROM tipo WHERE codigo_vivienda = ? AND id_inquilino = ?';
    
    PREPARE prepared_stmt FROM @declaracion_stmt;
    
    SET @p_codigo_vivienda = p_codigo_vivienda;
    SET @p_id_inquilino = p_id_inquilino;
    EXECUTE prepared_stmt
    USING @p_codigo_vivienda, @p_id_inquilino;
    
    SET hecho = ROW_COUNT();
    
    DEALLOCATE PREPARE prepared_stmt;
	
END//
DELIMITER ;



-- update



DELIMITER //
DROP PROCEDURE IF EXISTS sp_update_contrata//
CREATE PROCEDURE sp_update_contrata (
    IN p_id_inquilino INT,
    IN p_codigo_vivienda INT,
    IN modificacion VARCHAR(20),
    IN dato DATE,
    OUT hecho INT
)
BEGIN

	DECLARE datoN DECIMAL(10, 2);
    DECLARE EXIT HANDLER FOR SQLEXCEPTION SET hecho = -1;
    
    IF modificacion = 'precio' THEN
    
        SET datoN = CAST(dato AS DOUBLE);
        
        SET @declaracion_stmt  = CONCAT('UPDATE contrata SET ', modificacion, ' = ? WHERE id_inquilino = ? AND codigo_vivienda = ?');
    
		PREPARE prepared_stmt FROM @declaracion_stmt ;
    
		SET @datoN = datoN;
		SET @p_id_inquilino = p_id_inquilino;
		SET @p_codigo_vivienda = p_codigo_vivienda;
		EXECUTE prepared_stmt  
        USING @datoN, @p_id_inquilino, @p_codigo_vivienda;
    
		SET hecho = ROW_COUNT();
    
		DEALLOCATE PREPARE prepared_stmt;
        
	ELSE
    
        SET @declaracion_stmt  = CONCAT('UPDATE contrata SET ', modificacion, ' = ? WHERE id_inquilino = ? AND codigo_vivienda = ?');
    
		PREPARE prepared_stmt FROM @declaracion_stmt ;
    
		SET @dato = dato;
		SET @p_id_inquilino = p_id_inquilino;
		SET @p_codigo_vivienda = p_codigo_vivienda;
		EXECUTE prepared_stmt  USING 
		@dato, @p_id_inquilino, @p_codigo_vivienda;
    
		SET hecho = ROW_COUNT();
    
		DEALLOCATE PREPARE prepared_stmt;
    
    END IF;
END//
DELIMITER ;



