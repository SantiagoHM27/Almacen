package com.santiago.almacen.services;

import com.santiago.almacen.dto.productos.ProductoRequest;
import com.santiago.almacen.dto.productos.ProductoResponse;
import com.santiago.almacen.mappers.ProductoMapper;

import java.util.List;

public interface ProductoService {
    List<ProductoResponse> listar();

    ProductoResponse obtenerporId(Long id);

    ProductoResponse obtenerPorId(Long id);

    ProductoResponse registrar(ProductoRequest request);

    ProductoResponse actualizar(ProductoRequest request, Long id);

    void eliminar(Long id);
}
