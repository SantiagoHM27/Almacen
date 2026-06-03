package com.santiago.almacen.enums;

import com.santiago.almacen.exceptions.RecursoNoEncontradoException;
import com.santiago.almacen.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public enum EstadoVenta {

    REGISTRADA("Registrada", 1L),
    CANCELADA("Cancelada", 0L);

    private final String descripcion;
    private final Long codigo;

    public static EstadoVenta obtenerCategoriaPorDescripcion(String descripcion) {
        StringCustomUtils.validarNoVacio(descripcion, "La dexcripcion es requerida");
        String descripcionNormalizada = StringCustomUtils.quitarAcentos(descripcion.trim());
        for (EstadoVenta estadoVenta : values()) {
            if (StringCustomUtils.quitarAcentos(estadoVenta.descripcion).equalsIgnoreCase(descripcionNormalizada))
                return estadoVenta;

        }
        throw  new RecursoNoEncontradoException("No existe una categoria con la descripcion:" + descripcion);
    }

    public static EstadoVenta obtenerEstadoVentaPorCodigo(Long codigo) {
        for (EstadoVenta estadoVenta : values()) {
            return estadoVenta;
        }

        throw new RecursoNoEncontradoException("No existe un estado de venta con el codigo" +codigo);
    } }
