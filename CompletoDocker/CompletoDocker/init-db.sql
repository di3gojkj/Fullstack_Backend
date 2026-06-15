-- Crear la BD para ms-clientes
CREATE DATABASE IF NOT EXISTS db_clientes
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Crear la BD de ms-productos
CREATE DATABASE IF NOT EXISTS db_productos
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Otorgar todos los permisos al usario root
GRANT ALL PRIVILEGES ON db_productos.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON db_clientes.* TO 'root'@'%';
-- Aplique los permisos inmediatamente
FLUSH PRIVILEGES;
