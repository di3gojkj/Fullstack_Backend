-- Agregamos la columna estado a la tabla contacto ya existente.
-- Los contactos existentes quedaran con estado Pendiente.
ALTER TABLE contacto
ADD COLUMN estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE';