package com.demo.jdbc.domain;

import org.springframework.data.annotation.Id;

public class Curso {

    @Id
    private Long id;
    private Long alumnoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

}
