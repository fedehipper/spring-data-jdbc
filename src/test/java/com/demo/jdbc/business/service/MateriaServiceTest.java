package com.demo.jdbc.business.service;

import com.demo.jdbc.JdbcApplicationTests;
import com.demo.jdbc.domain.Especialidad;
import com.demo.jdbc.domain.Materia;
import com.demo.jdbc.repository.MateriaRepository;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MateriaServiceTest extends JdbcApplicationTests {

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private MateriaRepository materiaRepository;
    
    @Test
    public void guardar_materiaNueva_retornaMateriaGuardada() {
        Materia materia = new Materia();
        materia.setEspecialidad(Especialidad.K);
        materia.setCodigo("85-1347");
        materia.setNombre("Algoritmos y Estructura de Datos");

        Materia materiaGuardada = materiaService.guardar(materia);

        assertThat(materiaGuardada).isEqualToIgnoringGivenFields(materia, "id");
    }

    @Test
    public void buscarPorCodigo_materiaExiste_retornaMateria() {
        String codigoMateria = "85-1347";

        Materia materia = new Materia();
        materia.setEspecialidad(Especialidad.K);
        materia.setCodigo(codigoMateria);
        materia.setNombre("Algebra y Geometría Analítica");

        Materia materiaConCodigoABuscar = materiaRepository.save(materia);

        Materia materiaEncontrada = materiaService.buscarPorCodigo(codigoMateria);

        assertThat(materiaConCodigoABuscar).isEqualToComparingFieldByField(materiaEncontrada);

    }

    @Test
    public void buscarPorCodigo_materiaNoExiste_lanzaExcepcion() {
        String codigoMateriaNoExistente = "XXX";

        assertThatThrownBy(() -> materiaService.buscarPorCodigo(codigoMateriaNoExistente))
                .isInstanceOf(NoSuchElementException.class);
    }
    
    @Test
    public void eliminar_materiaExiste_retornaMateriaEliminada() {        
        String codigoMateria = "85-1347";
        Materia materia = new Materia();
        materia.setEspecialidad(Especialidad.K);
        materia.setCodigo(codigoMateria);
        materia.setNombre("Algebra y Geometría Analítica");
        
        materiaRepository.save(materia);
        
        Materia materiaEliminada = materiaService.eliminar(codigoMateria);
        
        assertThat(materia).isEqualToComparingFieldByField(materiaEliminada);

    }

}
