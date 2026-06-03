package com.InventarioModelo.Inventarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity

public class Movimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private String descripcion;
    private LocalDateTime fechaMovimiento;

    // --- RELACIONES CONO VALORES EN POSTGRESQL (Llaves Foráneas) ---

    @ManyToOne
    @JoinColumn(name = "Herramienta_Id", nullable = false)
    private Herramienta herramienta;

    @ManyToOne
    @JoinColumn(name = "proyecto_Id", nullable = false)
    private Proyecto proyecto;
}
