package com.InventarioModelo.Inventarios.controller;

import com.InventarioModelo.Inventarios.dto.HerramientaDTO;
import com.InventarioModelo.Inventarios.service.IHerramientaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
@RequiredArgsConstructor

public class HerramientaController {

    private final IHerramientaService herramientaService;

    @GetMapping
    public ResponseEntity<List<HerramientaDTO>> traerProductos(){

        return ResponseEntity.ok(herramientaService.traerHerramientas());
    }

    @PostMapping
    public ResponseEntity<HerramientaDTO> crearProducto(@RequestBody HerramientaDTO herramientaDTO){

        HerramientaDTO creado = herramientaService.crearHerramienta(herramientaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HerramientaDTO> actualizarProducto(@PathVariable Long id, @RequestBody HerramientaDTO herramientaDTO){

        return ResponseEntity.ok(herramientaService.modificarHerramienta(id, herramientaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){

        herramientaService.eliminarHerramienta(id);

        return ResponseEntity.noContent().build();
    }
}
