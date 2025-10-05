-- Script de base de datos para Catálogo Musical
-- Universidad Don Bosco - DSA441

-- Eliminar tablas si existen
DROP TABLE IF EXISTS canciones;
DROP TABLE IF EXISTS albumes;
DROP TABLE IF EXISTS artistas;

-- Crear tabla artistas
CREATE TABLE artistas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    genero TEXT NOT NULL,
    descripcion TEXT,
    imagen TEXT
);

-- Crear tabla albumes
CREATE TABLE albumes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    artista_id INTEGER NOT NULL,
    año INTEGER,
    genero TEXT,
    portada TEXT,
    FOREIGN KEY (artista_id) REFERENCES artistas(id)
);

-- Crear tabla canciones
CREATE TABLE canciones (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    artista_id INTEGER NOT NULL,
    album_id INTEGER NOT NULL,
    duracion TEXT,
    genero TEXT,
    FOREIGN KEY (artista_id) REFERENCES artistas(id),
    FOREIGN KEY (album_id) REFERENCES albumes(id)
);

-- Insertar datos de prueba - Artistas
INSERT INTO artistas (nombre, genero, descripcion) VALUES
('Bad Bunny', 'Reggaeton', 'Artista puertorriqueño de reggaeton y trap latino, considerado uno de los más influyentes de la música urbana'),
('Taylor Swift', 'Pop', 'Cantante y compositora estadounidense, conocida por sus letras autobiográficas y transición entre géneros'),
('Metallica', 'Heavy Metal', 'Banda estadounidense de heavy metal formada en 1981, pioneros del thrash metal'),
('Shakira', 'Pop Latino', 'Cantante, compositora y bailarina colombiana, conocida como la Reina del Pop Latino'),
('The Beatles', 'Rock', 'Banda británica de rock formada en Liverpool, considerada la más influyente de todos los tiempos'),
('Ed Sheeran', 'Pop', 'Cantante, compositor y guitarrista británico, conocido por sus mezclas de pop y folk');

-- Insertar datos de prueba - Álbumes
INSERT INTO albumes (titulo, artista_id, año, genero) VALUES
('Un Verano Sin Ti', 1, 2022, 'Reggaeton'),
('1989', 2, 2014, 'Pop'),
('Master of Puppets', 3, 1986, 'Heavy Metal'),
('Fijación Oral Vol. 1', 4, 2005, 'Pop Latino'),
('Abbey Road', 5, 1969, 'Rock'),
('÷ (Divide)', 6, 2017, 'Pop'),
('X', 6, 2014, 'Pop'),
('Reputation', 2, 2017, 'Pop'),
('El Dorado', 4, 2017, 'Pop Latino'),
('...And Justice for All', 3, 1988, 'Heavy Metal');

-- Insertar datos de prueba - Canciones
INSERT INTO canciones (titulo, artista_id, album_id, duracion, genero) VALUES
-- Canciones de Bad Bunny - Un Verano Sin Ti
('Moscow Mule', 1, 1, '4:05', 'Reggaeton'),
('Tití Me Preguntó', 1, 1, '4:03', 'Reggaeton'),
('Después de la Playa', 1, 1, '3:50', 'Reggaeton'),
('Me Porto Bonito', 1, 1, '2:58', 'Reggaeton'),

-- Canciones de Taylor Swift - 1989
('Shake It Off', 2, 2, '3:39', 'Pop'),
('Blank Space', 2, 2, '3:51', 'Pop'),
('Style', 2, 2, '3:51', 'Pop'),
('Bad Blood', 2, 2, '3:31', 'Pop'),

-- Canciones de Metallica - Master of Puppets
('Battery', 3, 3, '5:12', 'Heavy Metal'),
('Master of Puppets', 3, 3, '8:35', 'Heavy Metal'),
('Welcome Home (Sanitarium)', 3, 3, '6:27', 'Heavy Metal'),

-- Canciones de Shakira - Fijación Oral Vol. 1
('La Tortura', 4, 4, '3:35', 'Pop Latino'),
('No', 4, 4, '4:47', 'Pop Latino'),
('Día de Enero', 4, 4, '2:55', 'Pop Latino'),

-- Canciones de The Beatles - Abbey Road
('Come Together', 5, 5, '4:20', 'Rock'),
('Something', 5, 5, '3:03', 'Rock'),
('Here Comes the Sun', 5, 5, '3:05', 'Rock'),

-- Canciones de Ed Sheeran - ÷ (Divide)
('Shape of You', 6, 6, '3:53', 'Pop'),
('Castle on the Hill', 6, 6, '4:21', 'Pop'),
('Galway Girl', 6, 6, '2:50', 'Pop'),

-- Canciones adicionales de Ed Sheeran - X
('Sing', 6, 7, '3:55', 'Pop'),
('Don''t', 6, 7, '3:39', 'Pop'),

-- Canciones adicionales de Taylor Swift - Reputation
('Look What You Made Me Do', 2, 8, '3:31', 'Pop'),
('End Game', 2, 8, '4:04', 'Pop'),

-- Canciones adicionales de Shakira - El Dorado
('Chantaje', 4, 9, '3:15', 'Pop Latino'),
('Me Enamoré', 4, 9, '3:46', 'Pop Latino'),

-- Canciones adicionales de Metallica - ...And Justice for All
('One', 3, 10, '7:27', 'Heavy Metal'),
('Harvester of Sorrow', 3, 10, '5:45', 'Heavy Metal');

-- Consultas de verificación
SELECT 'Artistas insertados: ' || COUNT(*) FROM artistas;
SELECT 'Álbumes insertados: ' || COUNT(*) FROM albumes;
SELECT 'Canciones insertadas: ' || COUNT(*) FROM canciones;