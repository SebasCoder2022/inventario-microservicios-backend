package com.InventarioModelo.Inventarios.service;

import com.InventarioModelo.Inventarios.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO modificarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);

}
