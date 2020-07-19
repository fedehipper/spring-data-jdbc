package com.demo.jdbc.business.service;

import com.demo.jdbc.domain.Alumno;
import com.demo.jdbc.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public Alumno darDeAlta(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public void darDeBaja(Long alumnoId) {
        alumnoRepository.deleteById(alumnoId);
    }

}
