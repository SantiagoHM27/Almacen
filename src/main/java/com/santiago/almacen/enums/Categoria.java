package com.santiago.almacen.enums;


import com.santiago.almacen.exceptions.RecursoNoEncontradoException;
import com.santiago.almacen.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Categoria {
    ALIMENTO("Comida"),
    HIGIENE("Shampoo"),
    JUGUETE("Buz"),
    ELECTRONICA("iPhone"),
    ROPA("Playera"),
    ACCESORIO("Charm"),
    FARMACIA("sI");

    private  final String descripcion;

    public static Categoria obtenerCategoriaPorDescripcion(String descripcion) {
        StringCustomUtils.validarNoVacio(descripcion, "La dexcripcion es requerida");
        String descripcionNormalizada = StringCustomUtils.quitarAcentos(descripcion.trim());
        for (Categoria categoria : values()) {
            if (StringCustomUtils.quitarAcentos(categoria.descripcion).equalsIgnoreCase(descripcionNormalizada))
                return categoria;

        }
        throw  new RecursoNoEncontradoException("No existe una categoria con la descripcion:" + descripcion);
    }
}
