package com.demo.jdbc.controller.rest;

import com.demo.jdbc.business.service.MateriaService;
import com.demo.jdbc.domain.Materia;
import java.util.Set;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @DeleteMapping("/api/materia/{codigo}")
    public void eliminar(@PathVariable String codigo) {
        materiaService.eliminarPorCodigo(codigo);
    }
    
    @GetMapping("/api/materia/{codigo}")
    public Materia buscarPorCodigo(@PathVariable String codigo) {
        return materiaService.buscarPorCodigo(codigo);
    }
    
    @GetMapping("/api/materia")
    public Set<Materia> buscarTodas() {
        return materiaService.buscarTodas();
    }

}
