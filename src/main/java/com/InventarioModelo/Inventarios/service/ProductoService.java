package com.InventarioModelo.Inventarios.service;

import com.InventarioModelo.Inventarios.dto.ProductoDTO;
import com.InventarioModelo.Inventarios.model.Producto;
import com.InventarioModelo.Inventarios.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@RequiredArgsConstructor

public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> traerProductos() {

        // 1. Ir a la base de datos a buscar todos los productos reales
        List<Producto> productos = productoRepository.findAll();

        // 2. Convertir esa lista de entidades a una lista de DTOs
        return productos.stream()
                .map(this::convertirAProductoDTO)
                .toList();

    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {

        // 1. Convertir DTO a Entidad
        Producto producto = convertirAEntidad(productoDTO);

        // 2. Guardar en la base de datos de PostgreSQL
        Producto productoGuardado = productoRepository.save(producto);

        // 3. Convertir la Entidad guardada de vuelta a DTO para el retorno
        return convertirAProductoDTO(productoGuardado);

    }

    @Override
    public ProductoDTO modificarProducto(Long id, ProductoDTO productoDTO) {

        // 1. Buscamos el producto en Postgres. Si no existe, lanzamos un error limpio
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        // 2. Transmitimos los datos nuevos del DTO a la entidad que ya existía en la BD
        productoExistente.setNombre(productoDTO.getNombre());
        productoExistente.setCategoria(productoDTO.getCategoria());
        productoExistente.setPrecio(productoDTO.getPrecio());
        productoExistente.setCantidad(productoDTO.getCantidad());

        // 3. Guardamos los cambios (JPA detecta que ya tiene ID y hace un UPDATE en vez de un INSERT)
        Producto productoActualizado = productoRepository.save(productoExistente);

        // 4. Retornamos la respuesta mapeada de vuelta a DTO
        return convertirAProductoDTO(productoActualizado);

    }

    @Override
    public void eliminarProducto(Long id) {

        // 1. Validamos primero si el producto existe antes de intentar borrarlo
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Producto no encontrado con el ID: " + id));

        // 2. El repositorio se encarga de borrarlo físicamente de PostgreSQL
        productoRepository.delete(producto);

    }

    // --- MÉTODOS AUXILIARES DE MAPEO (Limpieza DRY) ---

    private ProductoDTO convertirAProductoDTO(Producto productoGuardado) {
        ProductoDTO resultadoDTO = new ProductoDTO();

        // Le asignamos los mismos datos (¡aquí puedes ver el flujo de ida y vuelta!)
        resultadoDTO.setNombre(productoGuardado.getNombre());
        resultadoDTO.setCategoria(productoGuardado.getCategoria());
        resultadoDTO.setPrecio(productoGuardado.getPrecio());
        resultadoDTO.setCantidad(productoGuardado.getCantidad());
        return resultadoDTO;
    }

    private Producto convertirAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setPrecio(dto.getPrecio());
        producto.setCantidad(dto.getCantidad());
        return producto;
    }

}
