package com.InventarioModelo.Inventarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class MovimientosDTO {

    private String tipo;
    private String descripcion;
    private int cantidad;
    private LocalDateTime fechaMovimiento;
    private Long herramientaId;
    private Long proyectoId;

}
