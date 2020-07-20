package com.demo.jdbc.repository;

import com.demo.jdbc.domain.Materia;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MateriaRepository extends CrudRepository<Materia, Long> {

    Optional<Materia> findByCodigo(String codigoMateria);

    @Modifying
    @Query("DELETE FROM materia WHERE codigo = :codigo")
    void deleteByCodigo(@Param("codigo") String codigo);

    @Override
    Set<Materia> findAll();

}
