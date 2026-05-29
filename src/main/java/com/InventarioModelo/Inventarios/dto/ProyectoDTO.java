package com.InventarioModelo.Inventarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProyectoDTO {

    private String nombre;
    private String ubicacion;
    private String ingenieroACargo;
    private String estado;

}
