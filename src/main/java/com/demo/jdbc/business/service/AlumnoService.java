package com.demo.jdbc.business.service;

import com.demo.jdbc.domain.Alumno;
import com.demo.jdbc.domain.Curso;
import com.demo.jdbc.repository.AlumnoRepository;
import java.util.Set;
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
        Alumno alumnoADarDeBaja = buscarPorId(alumnoId);
        alumnoRepository.deleteById(alumnoADarDeBaja.getId());
    }

    public Alumno buscarPorId(Long alumnoId) {
        return alumnoRepository.findById(alumnoId).get();
    }

    public Set<Alumno> buscarTodos() {
        return alumnoRepository.findAll();
    }

    public Alumno inscribirACurso(long alumnoId, long materiaId, String codigoCurso) {
        Alumno alumnoAInscribir = agregarCurso(buscarPorId(alumnoId), materiaId, codigoCurso);
        
        return alumnoRepository.save(alumnoAInscribir);
    }
    
    private Alumno agregarCurso(Alumno alumno, long materiaId, String codigoCurso) {
        Curso curso = new Curso();
        curso.setCodigo(codigoCurso);
        curso.setMateriaId(materiaId);

        alumno.getCursos().add(curso);

        return alumno;
    }

}
