package com.santiago.almacen.mappers;


import com.santiago.almacen.dto.ventas.DetalleVentaResponse;
import com.santiago.almacen.dto.ventas.VentaResponse;
import com.santiago.almacen.entities.DetalleVenta;
import com.santiago.almacen.entities.Venta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@AllArgsConstructor
public class VentaMapper {

    private final SucursalMapper sucursalMapper;

    public VentaResponse entidadAResponse(Venta venta) {
        List<DetalleVentaResponse> detalles = venta.getDetalleVenta().stream()
                .map(this::detalleAResponse)
                .toList();

        BigDecimal total = BigDecimal.ZERO;
        for (DetalleVentaResponse detalleDto : detalles) {
            total = total.add(detalleDto.subtotal());
        }

        return new VentaResponse(
                venta.getId(),
                venta.getFecha().toString(),
                venta.getEstadoVenta().getDescripcion(),
                sucursalMapper.entidadAResponse(venta.getSucursal()),
                detalles,
                total
        );
    }

    private DetalleVentaResponse detalleAResponse(DetalleVenta detalle) {
        return new DetalleVentaResponse(
                detalle.getId(),
                detalle.getProducto().getNombre(),
                detalle.getCantidadProducto(),
                detalle.getPrecioProducto(),
                detalle.calcularSubtotal()
        );
    }
}
