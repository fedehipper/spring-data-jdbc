package com.demo.jdbc.business.service;

import com.demo.jdbc.JdbcApplicationTests;
import com.demo.jdbc.domain.Alumno;
import com.demo.jdbc.repository.AlumnoRepository;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AlumnoServiceTest extends JdbcApplicationTests {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @BeforeEach
    public void setUp() {
        alumnoRepository.deleteAll();
    }
    
    @Test
    public void darDeAlta_alumnoNuevo_retornaAlumnoCreado() {
        Alumno alumno = new Alumno();
        alumno.setApellidoYNombre("Hipperdinger Federico");
        alumno.setFechaNacimiento(LocalDate.of(1992, 2, 28));
        alumno.setRegional("Buenos Aires");

        Alumno alumnoDadoDeAlta = alumnoService.darDeAlta(alumno);

        assertThat(alumnoDadoDeAlta).isEqualToIgnoringGivenFields(alumno, "id");

    }

    @Test
    public void darDeBaja_alumnoExiste_retornaAlumnoDadoDeBaja() {
        Alumno alumno = new Alumno();
        alumno.setApellidoYNombre("Hipperdinger Federico");
        alumno.setFechaNacimiento(LocalDate.of(1992, 2, 28));
        alumno.setRegional("Buenos Aires");

        Long idAlumnoADarDeBaja = alumnoRepository.save(alumno).getId();

        alumnoService.darDeBaja(idAlumnoADarDeBaja);

        assertThat(alumnoRepository.count()).isEqualTo(0);

    }

    @Test
    public void buscarPorId_alumnoExiste_retornaAlumno() {
        Alumno alumno = new Alumno();
        alumno.setApellidoYNombre("Hipperdinger Federico");
        alumno.setFechaNacimiento(LocalDate.of(1992, 2, 28));
        alumno.setRegional("Buenos Aires");

        Long alumnoId = alumnoRepository.save(alumno).getId();

        Alumno alumnoEncontrado = alumnoService.buscarPorId(alumnoId);

        assertThat(alumnoEncontrado.getId()).isEqualTo(alumnoId);
    }

    @Test
    public void buscarPorId_alumnoNoExiste_lanzaException() {
        Long alumnoIdNoExiste = alumnoRepository.count() + 1;

        assertThatThrownBy(() -> alumnoService.buscarPorId(alumnoIdNoExiste))
                .isInstanceOf(NoSuchElementException.class);
    }

}
