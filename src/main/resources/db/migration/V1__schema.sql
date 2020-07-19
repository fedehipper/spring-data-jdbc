CREATE TABLE materia (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR,
    codigo VARCHAR,
    especialidad VARCHAR
);

CREATE TABLE alumno(
    id BIGSERIAL PRIMARY KEY,
    apellido_y_nombre VARCHAR,
    fecha_nacimiento DATE,
    regional VARCHAR
);

CREATE TABLE curso (
    id BIGSERIAL PRIMARY KEY,
    alumno_id BIGINT,
    materia_id BIGINT,
    FOREIGN KEY (materia_id) REFERENCES materia(id),
    FOREIGN KEY (alumno_id) REFERENCES alumno(id)
);