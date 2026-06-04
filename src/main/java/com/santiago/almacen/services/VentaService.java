package com.santiago.almacen.services;

import com.santiago.almacen.dto.ventas.VentaRequest;
import com.santiago.almacen.dto.ventas.VentaResponse;

import java.util.List;

public interface VentaService {

    List<VentaResponse> listar();

    VentaResponse obtenerPorId(Long id);

    VentaResponse registrar(VentaRequest request);

    void cancelar(Long id);
    List<VentaResponse> listarCanceladas();
}
