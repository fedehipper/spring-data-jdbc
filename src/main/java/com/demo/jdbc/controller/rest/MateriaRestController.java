package com.demo.jdbc.controller.rest;

import com.demo.jdbc.business.service.MateriaService;
import com.demo.jdbc.domain.Materia;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MateriaRestController {

    private final MateriaService materiaService;

    public MateriaRestController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping("/api/materia")
    public Materia guardar(@RequestBody Materia materia) {
        return materiaService.guardar(materia);
    }
    
    @DeleteMapping("/api/materia")
    public void eliminar(@RequestParam String codigo) {
        materiaService.eliminarPorCodigo(codigo);
    }

}
