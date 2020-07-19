package com.demo.jdbc.business.service;

import com.demo.jdbc.domain.Alumno;
import com.demo.jdbc.domain.Curso;
import com.demo.jdbc.domain.Materia;
import com.demo.jdbc.repository.AlumnoRepository;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    private final MateriaService materiaService;

    public AlumnoService(AlumnoRepository alumnoRepository, MateriaService materiaService) {
        this.alumnoRepository = alumnoRepository;
        this.materiaService = materiaService;
    }

    public Alumno darDeAlta(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public void darDeBaja(Long alumnoId) {
        Alumno alumnoADarDeBaja = buscarPorId(alumnoId);
        alumnoRepository.deleteById(alumnoADarDeBaja.getId());
    }

    public Alumno buscarPorId(Long alumnoId) {
        return alumnoRepository
                .findById(alumnoId)
                .get();
    }

    public Set<Alumno> buscarTodos() {
        return alumnoRepository.findAll();
    }

    public Alumno inscribirACurso(long alumnoId, String codigoMateria, String codigoCurso) {
        Materia materia = materiaService.buscarPorCodigo(codigoMateria);
        Alumno alumnoAInscribir = agregarCurso(buscarPorId(alumnoId), materia.getId(), codigoCurso);

        return alumnoRepository.save(alumnoAInscribir);
    }

    private Alumno agregarCurso(Alumno alumno, long materiaId, String codigoCurso) {
        Curso curso = new Curso();
        curso.setCodigo(codigoCurso);
        curso.setMateriaId(materiaId);

        alumno.getCursos().add(curso);

        return alumno;
    }

    public Alumno desInscribirAlumno(Long alumnoId, String codigo, String codigoCurso) {
        Alumno alumnoADesInscribir = buscarPorId(alumnoId);

        Set<Curso> cursosActualizados = actualizarCursos(alumnoADesInscribir, codigoCurso);

        alumnoADesInscribir.setCursos(cursosActualizados);

        return alumnoRepository.save(alumnoADesInscribir);

    }

    private Set<Curso> actualizarCursos(Alumno alumno, String codigoCurso) {
        return alumno
                .getCursos()
                .stream()
                .filter(curso -> !codigoCurso.equals(curso.getCodigo()))
                .collect(toSet());
    }

}
