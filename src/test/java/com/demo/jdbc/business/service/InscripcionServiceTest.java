package com.demo.jdbc.business.service;

import com.demo.jdbc.JdbcApplicationTests;
import com.demo.jdbc.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class InscripcionServiceTest extends JdbcApplicationTests {

    @Autowired
    private InscripcionService inscripcionMateriasService;

    @Autowired
    private AlumnoRepository alumnoRepository;

//    @BeforeEach
//    public void setUp() {
//        alumnoRepository.deleteAll();
//    }


}
