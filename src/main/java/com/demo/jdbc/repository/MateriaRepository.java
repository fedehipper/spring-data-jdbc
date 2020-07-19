package com.demo.jdbc.repository;

import com.demo.jdbc.domain.Materia;
import org.springframework.data.repository.CrudRepository;

public interface MateriaRepository extends CrudRepository<Materia, Long> {
    
}
