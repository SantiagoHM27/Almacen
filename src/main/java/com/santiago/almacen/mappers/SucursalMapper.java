package com.santiago.almacen.mappers;

import com.santiago.almacen.dto.sucursales.SucursalRequest;
import com.santiago.almacen.dto.sucursales.SucursalResponse;
import com.santiago.almacen.entities.Sucursal;
import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {

    public Sucursal requestAEntidad(SucursalRequest request) {
        if(request == null) return null;

        return Sucursal.builder()
                .nombre(request.nombre().trim())
                .direccion(request.nombre().trim())
                .build();

    }

    public SucursalResponse entidadAResponse(Sucursal entidad) {
        if(entidad == null) return null;

        return new SucursalResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getDireccion()
        );
    }

}
