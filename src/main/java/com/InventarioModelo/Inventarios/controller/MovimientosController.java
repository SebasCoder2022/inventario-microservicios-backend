package com.InventarioModelo.Inventarios.controller;

import com.InventarioModelo.Inventarios.dto.MovimientosDTO;
import com.InventarioModelo.Inventarios.service.IMovimientosService;
import com.InventarioModelo.Inventarios.service.impl.MovimientosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor

public class MovimientosController {

    private final IMovimientosService movimientosService;

    @PostMapping
    public ResponseEntity<MovimientosDTO> crearMovimiento(@RequestBody MovimientosDTO movimientosDTO){

        MovimientosDTO creado = movimientosService.registrarMovimientos(movimientosDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

}
