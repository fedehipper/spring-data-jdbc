package com.demo.jdbc.domain;

import org.springframework.data.annotation.Id;

public class Curso {

    @Id
    private Long id;

    private Long materiaId;

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
