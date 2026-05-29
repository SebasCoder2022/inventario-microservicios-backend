package com.InventarioModelo.Inventarios.service;

import com.InventarioModelo.Inventarios.dto.ProyectoDTO;

import java.util.List;

public interface IProyectoService {

    List<ProyectoDTO> traerProyectos();
    ProyectoDTO crearProyecto(ProyectoDTO proyectoDTO);
    ProyectoDTO modificarProyecto(Long id, ProyectoDTO proyectoDTO);
    void eliminarProyecto(Long id);

}
