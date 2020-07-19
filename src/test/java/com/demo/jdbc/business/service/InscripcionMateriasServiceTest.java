package com.demo.jdbc.business.service;

import com.demo.jdbc.JdbcApplicationTests;
import com.demo.jdbc.repository.AlumnoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class InscripcionMateriasServiceTest extends JdbcApplicationTests {

    @Autowired
    private InscripcionMateriasService inscripcionMateriasService;

    @Autowired
    private AlumnoRepository alumnoRepository;

//    @BeforeEach
//    public void setUp() {
//        alumnoRepository.deleteAll();
//    }

    @Test
    public void darDeAlta_alumnoNuevo_retornaAlumnoCreado() {

    }

}
