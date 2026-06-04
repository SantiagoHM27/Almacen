package com.santiago.almacen.enums;

import com.santiago.almacen.exceptions.RecursoNoEncontradoException;
import com.santiago.almacen.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EstadoVenta {

    REGISTRADA("Registrada", 1L),
    CANCELADA("Cancelada", 0L);

    private final String descripcion;
    private final Long codigo;

    public static EstadoVenta obtenerPorDescripcion(String descripcion) {
        StringCustomUtils.validarNoVacio(descripcion, "La descripcion es requerida");
        String normalizada = StringCustomUtils.quitarAcentos(descripcion.trim());
        for (EstadoVenta estado : values()) {
            if (StringCustomUtils.quitarAcentos(estado.descripcion).equalsIgnoreCase(normalizada))
                return estado;
        }
        throw new RecursoNoEncontradoException("No existe un estado con la descripcion: " + descripcion);
    }

    public static EstadoVenta obtenerPorCodigo(Long codigo) {
        for (EstadoVenta estado : values()) {
            if (estado.codigo.equals(codigo))
                return estado;
        }
        throw new RecursoNoEncontradoException("No existe un estado con el codigo: " + codigo);
    }
}