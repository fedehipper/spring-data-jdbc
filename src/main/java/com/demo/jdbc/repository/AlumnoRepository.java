package com.demo.jdbc.repository;

import com.demo.jdbc.domain.Alumno;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
    
    @Override
    Set<Alumno> findAll();
    
}
