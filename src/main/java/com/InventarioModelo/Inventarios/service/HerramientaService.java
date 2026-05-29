package com.InventarioModelo.Inventarios.service;

import com.InventarioModelo.Inventarios.dto.HerramientaDTO;
import com.InventarioModelo.Inventarios.model.Herramienta;
import com.InventarioModelo.Inventarios.repository.HerramientaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@RequiredArgsConstructor

public class HerramientaService implements IHerramientaService {

    private final HerramientaRepository herramientaRepository;

    @Override
    public List<HerramientaDTO> traerHerramientas() {

        // 1. Ir a la base de datos a buscar todos los productos reales
        List<Herramienta> herramientas = herramientaRepository.findAll();

        // 2. Convertir esa lista de entidades a una lista de DTOs
        return herramientas.stream()
                .map(this::convertirAHerramientaDTO)
                .toList();

    }

    @Override
    public HerramientaDTO crearHerramienta(HerramientaDTO herramientaDTO) {

        // 1. Convertir DTO a Entidad
        Herramienta herramienta = convertirAEntidad(herramientaDTO);

        // 2. Guardar en la base de datos de PostgreSQL
        Herramienta herramientaGuardado = herramientaRepository.save(herramienta);

        // 3. Convertir la Entidad guardada de vuelta a DTO para el retorno
        return convertirAHerramientaDTO(herramientaGuardado);

    }

    @Override
    public HerramientaDTO modificarHerramienta(Long id, HerramientaDTO herramientaDTO) {

        // 1. Buscamos la herramienta en Postgres. Si no existe, lanzamos un error limpio
        Herramienta herramientaExistente = herramientaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        // 2. Transmitimos los datos nuevos del DTO a la entidad que ya existía en la BD
        herramientaExistente.setNombre(herramientaDTO.getNombre());
        herramientaExistente.setCategoria(herramientaDTO.getCategoria());
        herramientaExistente.setPrecio(herramientaDTO.getPrecio());
        herramientaExistente.setCantidad(herramientaDTO.getCantidad());

        // 3. Guardamos los cambios (JPA detecta que ya tiene ID y hace un UPDATE en vez de un INSERT)
        Herramienta herramientaActualizado = herramientaRepository.save(herramientaExistente);

        // 4. Retornamos la respuesta mapeada de vuelta a DTO
        return convertirAHerramientaDTO(herramientaActualizado);

    }

    @Override
    public void eliminarHerramienta(Long id) {

        // 1. Validamos primero si el producto existe antes de intentar borrarlo
        Herramienta herramienta = herramientaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Producto no encontrado con el ID: " + id));

        // 2. El repositorio se encarga de borrarlo físicamente de PostgreSQL
        herramientaRepository.delete(herramienta);

    }

    // --- MÉTODOS AUXILIARES DE MAPEO (Limpieza DRY) ---

    private HerramientaDTO convertirAHerramientaDTO(Herramienta herramientaGuardado) {
        HerramientaDTO resultadoDTO = new HerramientaDTO();

        // Le asignamos los mismos datos (¡aquí puedes ver el flujo de ida y vuelta!)
        resultadoDTO.setNombre(herramientaGuardado.getNombre());
        resultadoDTO.setCategoria(herramientaGuardado.getCategoria());
        resultadoDTO.setPrecio(herramientaGuardado.getPrecio());
        resultadoDTO.setCantidad(herramientaGuardado.getCantidad());
        return resultadoDTO;
    }

    private Herramienta convertirAEntidad(HerramientaDTO dto) {
        Herramienta herramienta = new Herramienta();
        herramienta.setNombre(dto.getNombre());
        herramienta.setCategoria(dto.getCategoria());
        herramienta.setPrecio(dto.getPrecio());
        herramienta.setCantidad(dto.getCantidad());
        return herramienta;
    }

}
