DROP TABLE IF EXISTS materia;
CREATE TABLE materia (
    id bigint IDENTITY PRIMARY KEY,
    nombre VARCHAR(50),
    codigo VARCHAR(10),
    especialidad VARCHAR(1)
);