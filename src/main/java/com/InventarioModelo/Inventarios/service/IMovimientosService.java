package com.InventarioModelo.Inventarios.service;

import com.InventarioModelo.Inventarios.dto.MovimientosDTO;
import java.util.List;

public interface IMovimientosService {

    // El contrato para registrar las entradas y salidas de la bodega
    MovimientosDTO registrarMovimientos(MovimientosDTO movimientosDTO);

    // Contrato opcional por si queremos ver el historial completo en Postman
    List<MovimientosDTO> traerHistorial();

}
