CREATE TABLE materia (
    "ID" BIGSERIAL PRIMARY KEY,
    "NOMBRE" VARCHAR,
    "CODIGO" VARCHAR UNIQUE,
    "ESPECIALIDAD" VARCHAR
);

CREATE TABLE alumno(
    "ID" BIGSERIAL PRIMARY KEY,
    "APELLIDO_Y_NOMBRE" VARCHAR,
    "FECHA_NACIMIENTO" DATE,
    "REGIONAL" VARCHAR
);

CREATE TABLE curso (
    "ID" BIGSERIAL PRIMARY KEY,
    "CODIGO" VARCHAR,
    "ALUMNO_ID" BIGINT,
    "MATERIA_ID" BIGINT,
    FOREIGN KEY ("MATERIA_ID") REFERENCES materia("ID"),
    FOREIGN KEY ("ALUMNO_ID") REFERENCES alumno("ID")
);

-- POSTGRES crea por defecto columnas en minúscula, por lo que hay que poner
-- comillas dobles para que estén en mayusculas.