package com.demo.jdbc.business.service;

import com.demo.jdbc.JdbcApplicationTests;
import com.demo.jdbc.domain.Alumno;
import com.demo.jdbc.domain.Curso;
import com.demo.jdbc.domain.Materia;
import com.demo.jdbc.repository.AlumnoRepository;
import com.demo.jdbc.repository.MateriaRepository;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Set;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
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

    @Autowired
    private MateriaRepository materiaRepository;

    @BeforeEach
    public void setUp() {
        alumnoRepository.deleteAll();
        materiaRepository.deleteAll();
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
    public void darDeBaja_alumnoNoExiste_lanzaExcepcion() {
        Long alumnoIdNoExiste = alumnoRepository.count() + 1;

        assertThatThrownBy(() -> alumnoService.darDeBaja(alumnoIdNoExiste))
                .isInstanceOf(NoSuchElementException.class);
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

    @Test
    public void buscarTodos_alumnosExisten_retornaTodosLosAlumnos() {
        Alumno unAlumno = new Alumno();
        unAlumno.setApellidoYNombre("Hipperdinger Federico");
        unAlumno.setFechaNacimiento(LocalDate.of(1992, 2, 28));
        unAlumno.setRegional("Buenos Aires");

        Long unAlumnoId = alumnoRepository.save(unAlumno).getId();

        Alumno otroAlumno = new Alumno();
        otroAlumno.setApellidoYNombre("Cachirulo Pepito");
        otroAlumno.setFechaNacimiento(LocalDate.of(1990, 12, 28));
        otroAlumno.setRegional("Buenos Aires");

        Long otroAlumnoId = alumnoRepository.save(otroAlumno).getId();

        Set<Alumno> alumnos = alumnoService.buscarTodos();

        assertThat(alumnos.stream().map(alumno -> alumno.getId()).collect(toSet()))
                .isEqualTo(Set.of(otroAlumnoId, unAlumnoId));

    }

    @Test
    public void inscribirAlumno_alumnoExistente_retornaAlumnoConCursosIncluidoElNuevo() {

        Alumno alumno = new Alumno();
        alumno.setApellidoYNombre("Hipperdinger Federico");
        alumno.setFechaNacimiento(LocalDate.of(1992, 2, 28));
        alumno.setRegional("Buenos Aires");

        Long alumnoId = alumnoRepository.save(alumno).getId();

        Materia materia = new Materia();
        materia.setEspecialidad("K");
        materia.setCodigo("85-1347");
        materia.setNombre("Algoritmos y Estructura de Datos");

        Long materiaId = materiaRepository.save(materia).getId();

        String codigoCurso = "K1045";

        Alumno alumnoInscripto = alumnoService.inscribirACurso(alumnoId, materia.getCodigo(), codigoCurso);

        Curso cursoEsperado = new Curso();
        cursoEsperado.setCodigo(codigoCurso);
        cursoEsperado.setId(1L);
        cursoEsperado.setMateriaId(materiaId);

        assertThat(alumnoInscripto.getCursos().stream().collect(toList()).get(0)).isEqualToComparingFieldByField(cursoEsperado);

    }

    @Test
    public void inscribirAlumno_alumnoNoExiste_lanzaExcepcion() {

        Long alumnoIdNoExiste = alumnoRepository.count() + 1;

        Materia materia = new Materia();
        materia.setEspecialidad("K");
        materia.setCodigo("85-1347");
        materia.setNombre("Algoritmos y Estructura de Datos");

        materiaRepository.save(materia).getId();

        String codigoCurso = "K1045";

        assertThatThrownBy(() -> alumnoService.inscribirACurso(alumnoIdNoExiste, materia.getCodigo(), codigoCurso))
                .isInstanceOf(NoSuchElementException.class);

    }

    @Test
    public void inscribirAlumno_materiaIdNoExiste_lanzaExcepcion() {

        Alumno alumno = new Alumno();
        alumno.setApellidoYNombre("Hipperdinger Federico");
        alumno.setFechaNacimiento(LocalDate.of(1992, 2, 28));
        alumno.setRegional("Buenos Aires");

        Long alumnoId = alumnoRepository.save(alumno).getId();

        String codigoMateriaNoExiste = "XXXX";

        String codigoCurso = "K1045";

        assertThatThrownBy(() -> alumnoService.inscribirACurso(alumnoId, codigoMateriaNoExiste, codigoCurso))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void desInscribirAlumno_alumnoExiste_seEliminaLaRelacionCursoEntreMateriaYAlumno() {

        Alumno alumno = new Alumno();
        alumno.setApellidoYNombre("Hipperdinger Federico");
        alumno.setFechaNacimiento(LocalDate.of(1992, 2, 28));
        alumno.setRegional("Buenos Aires");

        Long alumnoId = alumnoRepository.save(alumno).getId();

        Materia materia = new Materia();
        materia.setEspecialidad("K");
        materia.setCodigo("85-1347");
        materia.setNombre("Algoritmos y Estructura de Datos");

        materiaRepository.save(materia);

        String codigoCurso = "K1045";

        alumnoService.inscribirACurso(alumnoId, materia.getCodigo(), codigoCurso);

        Alumno alumnoDesInscripto = alumnoService.desInscribirAlumno(alumnoId, materia.getCodigo(), codigoCurso);

        assertThat(alumnoDesInscripto.getCursos()).isEmpty();

    }

}
