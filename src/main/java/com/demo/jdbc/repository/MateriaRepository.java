package com.demo.jdbc.repository;

import com.demo.jdbc.domain.Materia;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface MateriaRepository extends CrudRepository<Materia, Long> {

    Optional<Materia> findByCodigo(String codigoMateria);

}
