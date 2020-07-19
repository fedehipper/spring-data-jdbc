package com.demo.jdbc.domain;

import java.time.LocalDate;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

public class Alumno {

    @Id
    private Long id;
    private String apellidoYNombre;
    private LocalDate fechaNacimiento;
    private String regional;
    @MappedCollection(idColumn = "ALUMNO_ID")
    private Set<Curso> cursos;

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellidoYNombre() {
        return apellidoYNombre;
    }

    public void setApellidoYNombre(String apellidoYNombre) {
        this.apellidoYNombre = apellidoYNombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

}
