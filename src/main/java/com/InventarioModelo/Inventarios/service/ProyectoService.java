package com.InventarioModelo.Inventarios.service;

import com.InventarioModelo.Inventarios.dto.ProyectoDTO;
import com.InventarioModelo.Inventarios.model.Proyecto;
import com.InventarioModelo.Inventarios.repository.ProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@RequiredArgsConstructor

public class ProyectoService implements IProyectoService {

    private final ProyectoRepository proyectoRepository;

    @Override
    public List<ProyectoDTO> traerProyectos() {

        // 1. Ir a la base de datos a buscar todos los proyectos
        List<Proyecto> proyectos = proyectoRepository.findAll();

        // 2. Convertir esa lista de entidades a una lista de DTOs
        return proyectos.stream()
                .map(this::convertirAProyectoDTO)
                .toList();

    }

    @Override
    public ProyectoDTO crearProyecto(ProyectoDTO proyectoDTO) {

        // 1. Convertir DTO a Entidad
        Proyecto proyecto = convertirAEntidad(proyectoDTO);

        // 2. Guardar en la base de datos de PostgreSQL
        Proyecto proyectoGuardado = proyectoRepository.save(proyecto);

        // 3. Convertir la Entidad guardada de vuelta a DTO para el retorno
        return convertirAProyectoDTO(proyectoGuardado);

    }

    @Override
    public ProyectoDTO modificarProyecto(Long id, ProyectoDTO proyectoDTO) {

        // 1. Buscamos el proyecto  en Postgres. Si no existe, lanzamos un error limpio
        Proyecto proyectoExistente = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con el ID: " + id));

        // 2. Transmitimos los datos nuevos del DTO a la entidad que ya existía en la BD

        proyectoExistente.setNombre(proyectoDTO.getNombre());
        proyectoExistente.setUbicacion(proyectoDTO.getUbicacion());
        proyectoExistente.setIngenieroACargo(proyectoDTO.getIngenieroACargo());
        proyectoExistente.setEstado(proyectoDTO.getEstado());

        Proyecto proyectoActualizado = proyectoRepository.save(proyectoExistente);

        return convertirAProyectoDTO(proyectoActualizado);

    }

    @Override
    public void eliminarProyecto(Long id) {

        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Proyecto no encontrado con el ID: " + id));

        proyectoRepository.delete(proyecto);

    }

    // --- MÉTODOS AUXILIARES DE MAPEO (Limpieza DRY) ---

    private ProyectoDTO convertirAProyectoDTO(Proyecto proyecto) {
        ProyectoDTO resultadoDTO = new ProyectoDTO();
        resultadoDTO.setNombre(proyecto.getNombre());
        resultadoDTO.setUbicacion(proyecto.getUbicacion());
        resultadoDTO.setIngenieroACargo(proyecto.getIngenieroACargo());
        resultadoDTO.setEstado(proyecto.getEstado());

        return resultadoDTO;
    }

    private Proyecto convertirAEntidad(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(proyectoDTO.getNombre());
        proyecto.setUbicacion(proyectoDTO.getUbicacion());
        proyecto.setIngenieroACargo(proyectoDTO.getIngenieroACargo());
        proyecto.setEstado(proyectoDTO.getEstado());

        return proyecto;
    }

}