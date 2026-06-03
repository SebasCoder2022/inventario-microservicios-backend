package com.InventarioModelo.Inventarios.service.impl;

import com.InventarioModelo.Inventarios.dto.MovimientosDTO;
import com.InventarioModelo.Inventarios.model.Herramienta;
import com.InventarioModelo.Inventarios.model.Movimientos;
import com.InventarioModelo.Inventarios.model.Proyecto;
import com.InventarioModelo.Inventarios.repository.HerramientaRepository;
import com.InventarioModelo.Inventarios.repository.MovimientosRepository;
import com.InventarioModelo.Inventarios.repository.ProyectoRepository;
import com.InventarioModelo.Inventarios.service.IMovimientosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service

@RequiredArgsConstructor

public class MovimientosService implements IMovimientosService {

    private final MovimientosRepository movimientosRepository;
    private final HerramientaRepository herramientaRepository;
    private final ProyectoRepository proyectoRepository;

    @Override
    public MovimientosDTO registrarMovimientos(MovimientosDTO movimientosDTO) {

        Herramienta herramienta = herramientaRepository.findById(movimientosDTO.getHerramientaId())
                .orElseThrow(() -> new RuntimeException("Herramienta no encontrada con el id" + movimientosDTO.getHerramientaId()));

        Proyecto proyecto = proyectoRepository.findById(movimientosDTO.getProyectoId())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        Movimientos movimientos = new Movimientos();

        movimientos.setTipo(movimientosDTO.getTipo());
        movimientos.setDescripcion(movimientosDTO.getDescripcion());

        movimientos.setFechaMovimiento(LocalDateTime.now());

        movimientos.setHerramienta(herramienta);
        movimientos.setProyecto(proyecto);

        Movimientos movimientosActualizado = movimientosRepository.save(movimientos);

        return convertirAMovimientosDTO(movimientosActualizado);
    }

    @Override
    public List<MovimientosDTO> traerHistorial() {

        return List.of();
    }

    // --- MÉTODOS AUXILIARES DE MAPEO (Limpieza DRY) ---

    private MovimientosDTO  convertirAMovimientosDTO(Movimientos movimientos) {

        MovimientosDTO movimientosDTO = new MovimientosDTO();

        movimientosDTO.setTipo(movimientos.getTipo());
        movimientosDTO.setDescripcion(movimientos.getDescripcion());
        movimientosDTO.setFechaMovimiento(movimientos.getFechaMovimiento());
        movimientosDTO.setHerramientaId(movimientos.getHerramienta().getId());
        movimientosDTO.setProyectoId(movimientos.getProyecto().getId());

        return movimientosDTO;
    }

}
