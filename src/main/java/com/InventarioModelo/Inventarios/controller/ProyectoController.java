package com.InventarioModelo.Inventarios.controller;

import com.InventarioModelo.Inventarios.dto.ProyectoDTO;
import com.InventarioModelo.Inventarios.service.IProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor

public class ProyectoController {

    private  final IProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> traerProyectos(){

        return ResponseEntity.ok(proyectoService.traerProyectos());
    }

    @PostMapping
    public ResponseEntity<ProyectoDTO> crearProyecto(@RequestBody ProyectoDTO proyectoDTO){

        ProyectoDTO creado = proyectoService.crearProyecto(proyectoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoDTO>  actualizarProyecto(@PathVariable Long id,  @RequestBody ProyectoDTO proyectoDTO){

        return ResponseEntity.ok(proyectoService.modificarProyecto(id, proyectoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Long id){

        proyectoService.eliminarProyecto(id);

        return ResponseEntity.noContent().build();
    }
}
