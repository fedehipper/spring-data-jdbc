package com.demo.jdbc.repository;

import com.demo.jdbc.domain.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
    
}
