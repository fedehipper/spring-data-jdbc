package com.demo.jdbc.business.service;

import com.demo.jdbc.JdbcApplicationTests;
import com.demo.jdbc.domain.Materia;
import com.demo.jdbc.repository.MateriaRepository;
import java.util.NoSuchElementException;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MateriaServiceTest extends JdbcApplicationTests {

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private MateriaRepository materiaRepository;

    @BeforeEach
    public void setUp() {
        materiaRepository.deleteAll();
    }

    @Test
    public void guardar_materiaNueva_retornaMateriaGuardada() {
        Materia materia = new Materia();
        materia.setEspecialidad("K");
        materia.setCodigo("85-1347");
        materia.setNombre("Algoritmos y Estructura de Datos");

        Materia materiaGuardada = materiaService.guardar(materia);

        assertThat(materiaGuardada).isEqualToIgnoringGivenFields(materia, "id");
    }

    @Test
    public void buscarPorCodigo_materiaExiste_retornaMateria() {
        String codigoMateria = "85-1347";

        Materia materia = new Materia();
        materia.setEspecialidad("K");
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
    public void eliminarPorCodigo_materiaExiste_retornaMateriaEliminada() {
        String codigoMateria = "85-1347";
        Materia materia = new Materia();
        materia.setEspecialidad("K");
        materia.setCodigo(codigoMateria);
        materia.setNombre("Algebra y Geometría Analítica");

        materiaRepository.save(materia);

        materiaService.eliminarPorCodigo(codigoMateria);

        assertThat(materiaRepository.count()).isEqualTo(0);
    }

    @Test
    public void eliminarPorCodigo_materiaNoExiste_lanzaExcepcion() {
        String codigoMateriaNoExistente = "XXX";

        assertThatThrownBy(() -> materiaService.eliminarPorCodigo(codigoMateriaNoExistente))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void buscarTodas_materiasExistentes_retornaTodasLasMaterias() {
        Materia unaMateria = new Materia();
        unaMateria.setEspecialidad("K");
        unaMateria.setCodigo("85-1347");
        unaMateria.setNombre("Algoritmos y Estructura de Datos");
        materiaRepository.save(unaMateria);

        Materia otraMateria = new Materia();
        otraMateria.setEspecialidad("K");
        otraMateria.setCodigo("85-1348");
        otraMateria.setNombre("Física II");
        materiaRepository.save(otraMateria);

        Set<Materia> todasLasMaterias = materiaService.buscarTodas();

        assertThat(todasLasMaterias.stream().map(materia -> materia.getCodigo()).collect(toSet()))
                .isEqualTo(Set.of(otraMateria.getCodigo(), unaMateria.getCodigo()));

    }

}
