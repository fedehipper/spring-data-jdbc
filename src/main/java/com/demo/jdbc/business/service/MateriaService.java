package com.demo.jdbc.business.service;

import com.demo.jdbc.domain.Materia;
import com.demo.jdbc.repository.MateriaRepository;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public Materia guardar(Materia materia) {
        return materiaRepository.save(materia);
    }

    public Materia buscarPorCodigo(String codigoMateria) {
        return materiaRepository
                .findByCodigo(codigoMateria)
                .get();
    }
    
    public void eliminarPorCodigo(String codigoMateria) {
        Materia materiaAEliminar = buscarPorCodigo(codigoMateria);
        materiaRepository.deleteByCodigo(materiaAEliminar.getCodigo());
    }

    public Set<Materia> buscarTodas() {
        return materiaRepository.findAll();
    }

}
