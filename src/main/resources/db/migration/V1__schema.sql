CREATE TABLE CUSTOMER (
    id bigserial PRIMARY KEY,
    first_name VARCHAR,
    dob DATE
);

CREATE TABLE STUDENT (
    id bigserial PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE REGISTRATION (
    id bigserial PRIMARY KEY,
    tipo VARCHAR,
    student_id BIGINT,
    foreign key (student_id) references STUDENT(id)
);

CREATE TABLE materia (
    id bigserial PRIMARY KEY,
    nombre VARCHAR,
    codigo VARCHAR,
    especialidad VARCHAR
);