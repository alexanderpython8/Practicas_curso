INSERT INTO propietario(DNI, nombre, email, telefono)
	VALUES ('00000A', 'alejandro', 'a@gmail.com', '11111');
    
INSERT INTO propietario(DNI, nombre, email, telefono)
	VALUES ('11111A', 'pepe', 'a@gmail.com', '11111');
    
INSERT INTO propietario(DNI, nombre, email, telefono)
	VALUES ('22222A', 'adrian', 'a@gmail.com', '11111');
    
INSERT INTO inquilino(DNI, nombre, email, telefono, mascota)
	VALUES ('00000B', 'Victor', 'a@gmail.com', '11111', 0);
    
INSERT INTO inquilino(DNI, nombre, email, telefono, mascota)
	VALUES ('11111B', 'Lorenzo', 'a@gmail.com', '11111', 1);
    
INSERT INTO inquilino(DNI, nombre, email, telefono, mascota)
	VALUES ('22222B', 'Jose', 'a@gmail.com', '11111', 1);
    
INSERT INTO vivienda(codigo, id_propietario, superficie, descripcion, mascotas, precio_mensual, direccion)
	VALUES (1, 1, '20km', 'Comodo', 0, 24.3, 'Calle Riquelme');
    
INSERT INTO vivienda(codigo, id_propietario, superficie, descripcion, mascotas, precio_mensual, direccion)
	VALUES (2, 2, '80km', 'Espacioso', 1, 280.83, 'Calle Ilia Topuria'); 
    
INSERT INTO vivienda(codigo, id_propietario, superficie, descripcion, mascotas, precio_mensual, direccion)
	VALUES (3, 3, '60km', 'Luminoso', 1, 90, 'Calle Programacion');
    
INSERT INTO contrata(id_inquilino, codigo_vivienda, precio, fecha_inicio, fecha_fin)
	VALUES(1, 2, 45000, '2024-11-09', '2052-11-09');
    
INSERT INTO contrata(id_inquilino, codigo_vivienda, precio, fecha_inicio, fecha_fin)
	VALUES(3, 1, 90000, '2026-10-22', '2040-10-22');
    
INSERT INTO contrata(id_inquilino, codigo_vivienda, precio, fecha_inicio, fecha_fin)
	VALUES(2, 3, 76000, '2012-03-12', '2024-06-24');
    
INSERT INTO tipo(codigo_vivienda, tipo_vivienda)
	VALUES(1, 'atico');
    
INSERT INTO tipo(codigo_vivienda, tipo_vivienda)
	VALUES(2, 'casa');
    
INSERT INTO tipo(codigo_vivienda, tipo_vivienda)
	VALUES(3, 'departamento');