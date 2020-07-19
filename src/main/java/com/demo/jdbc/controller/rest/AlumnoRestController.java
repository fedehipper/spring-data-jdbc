package com.demo.jdbc.controller.rest;

import com.demo.jdbc.business.service.AlumnoService;
import com.demo.jdbc.domain.Alumno;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlumnoRestController {

    private final AlumnoService alumnoService;

    public AlumnoRestController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping("/api/alumno")
    public Alumno darDeAlta(@RequestBody Alumno alumno) {
        return alumnoService.darDeAlta(alumno);
    }

    @DeleteMapping("/api/alumno/{id}")
    public void darDeBaja(@PathVariable long id) {
        alumnoService.darDeBaja(id);
    }

}
