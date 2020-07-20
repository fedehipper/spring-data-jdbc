package com.demo.jdbc.controller.rest;

import com.demo.jdbc.business.service.AlumnoService;
import com.demo.jdbc.domain.Alumno;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/api/alumno/{id}")
    public Alumno buscarPorId(@PathVariable long id) {
        return alumnoService.buscarPorId(id);
    }
    
    @PutMapping("/api/alumno/{id}/inscripcion")
    public Alumno inscribirACurso(@PathVariable long id, @RequestParam String codigoMateria, @RequestParam String codigoCurso) {
        return alumnoService.inscribirACurso(id, codigoMateria, codigoCurso);
    }
    
    @PutMapping("/api/alumno/{id}/baja")
    public Alumno desInscribirACurso(@PathVariable long id, @RequestParam String codigoMateria, @RequestParam String codigoCurso) {
        return alumnoService.desInscribirAlumno(id, codigoMateria, codigoCurso);
    }

}
