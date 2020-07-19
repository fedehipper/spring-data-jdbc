package com.demo.jdbc.business.service;

import com.demo.jdbc.JdbcApplicationTests;
import com.demo.jdbc.domain.Especialidad;
import com.demo.jdbc.domain.Materia;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MateriaServiceTest extends JdbcApplicationTests {

    @Autowired
    private MateriaService materiaService;
    
    @Test
    public void guardar_materiaNueva_retornaMateriaGuardada() {
        Materia materia = new Materia();
        materia.setEspecialidad(Especialidad.K);
        materia.setNombre("Algoritmos y Estructura de Datos");
        
        Materia materiaGuardada = materiaService.guardar(materia);
        
        assertThat(materiaGuardada).isEqualToIgnoringGivenFields(materia, "id");
    }
    
    
}
