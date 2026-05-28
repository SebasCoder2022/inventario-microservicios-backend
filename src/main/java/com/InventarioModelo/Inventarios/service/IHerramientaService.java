package com.InventarioModelo.Inventarios.service;

import com.InventarioModelo.Inventarios.dto.HerramientaDTO;

import java.util.List;

public interface IHerramientaService {

    List<HerramientaDTO> traerHerramientas();
    HerramientaDTO crearHerramienta(HerramientaDTO herramientaDTO);
    HerramientaDTO modificarHerramienta(Long id, HerramientaDTO herramientaDTO);
    void eliminarHerramienta(Long id);

}