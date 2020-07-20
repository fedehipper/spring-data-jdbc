CREATE TABLE materia (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR,
    codigo VARCHAR UNIQUE,
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
    codigo VARCHAR,
    "ALUMNO_ID" BIGINT,
    materia_id BIGINT,
    FOREIGN KEY (materia_id) REFERENCES materia(id),
    FOREIGN KEY ("ALUMNO_ID") REFERENCES alumno(id)
);

-- POSTGRES crea por defecto columnas en minúscula, por lo que hay que poner
-- comillas dobles para que estén en mayusculas.