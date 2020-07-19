DROP TABLE IF EXISTS materia;
DROP TABLE IF EXISTS curso;
DROP TABLE IF EXISTS alumno;

CREATE TABLE materia (
    id BIGINT IDENTITY PRIMARY KEY,
    nombre VARCHAR(50),
    codigo VARCHAR(10),
    especialidad VARCHAR(1)
);

CREATE TABLE alumno(
    id BIGINT IDENTITY PRIMARY KEY,
    apellido_y_nombre VARCHAR(50),
    fecha_nacimiento DATE,
    regional VARCHAR(50)
);

CREATE TABLE curso (
    id bigint IDENTITY PRIMARY KEY,
    alumno_id BIGINT,
    materia_id BIGINT,
    FOREIGN KEY (materia_id) REFERENCES materia(id),
    FOREIGN KEY (alumno_id) REFERENCES alumno(id)
);