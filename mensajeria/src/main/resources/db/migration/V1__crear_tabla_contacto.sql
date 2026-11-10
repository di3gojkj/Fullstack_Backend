CREATE TABLE contacto(
    id BIGINT NOT NULL AUTO_INCREMENT,      -- Identificador unico generado automaticamente por MySQL.
    nombre_completo VARCHAR(150) NOT NULL,  -- Nombre completo de quien envia el mensaje.
    correo VARCHAR(150) NOT NULL,            -- Correo electronico de contacto.
    mensaje TEXT NOT NULL,                  -- Contenido del mensaje. TEXT permite guardar textos extensos.
    fecha_creacion DATETIME(6) NOT NULL,    -- fecha y hora de creacion, asignadas desde el modelo con @prePersist.
    PRIMARY KEY(id)                         -- Defiene el identificador como clave primaria.
);