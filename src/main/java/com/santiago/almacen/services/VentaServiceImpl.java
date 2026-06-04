package com.santiago.almacen.services;


import com.santiago.almacen.dto.ventas.VentaRequest;
import com.santiago.almacen.dto.ventas.VentaResponse;
import com.santiago.almacen.entities.DetalleVenta;
import com.santiago.almacen.entities.Producto;
import com.santiago.almacen.entities.Sucursal;
import com.santiago.almacen.entities.Venta;
import com.santiago.almacen.enums.EstadoVenta;
import com.santiago.almacen.exceptions.RecursoNoEncontradoException;
import com.santiago.almacen.mappers.VentaMapper;
import com.santiago.almacen.repositories.ProductoRepository;
import com.santiago.almacen.repositories.SucursalRepository;
import com.santiago.almacen.repositories.VentaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;
    private final VentaMapper ventaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponse> listar() {
        log.info("listando todas las ventas registradas");
        return ventaRepository.findByEstadoVenta( EstadoVenta.REGISTRADA)
                .stream().map(ventaMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VentaResponse obtenerPorId(Long id) {
        log.info("Buscando la venta registrada con el id: {}", id);
        Venta venta = ventaRepository.findByIdAndEstadoVenta(id, EstadoVenta.REGISTRADA)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Venta registrada no encontrada con el id: "+id));
        return ventaMapper.entidadAResponse(venta);
    }

    @Override
    public VentaResponse registrar(VentaRequest request) {
        log.info("Registrando nueva venta para sucursal id: {}", request.idSucursal());

        Sucursal sucursal = sucursalRepository.findById(request.idSucursal())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Sucursal no encontrada con id: "+ request.idSucursal()));
        Venta venta = Venta.builder()
                .estadoVenta(EstadoVenta.REGISTRADA)
                .fecha(LocalDate.now())
                .sucursal(sucursal)
                .build();

        request.productos().forEach(detalleReq -> {
            Producto producto = productoRepository.findById(detalleReq.idProducto())
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                            "Producto no encontrado con id: " + detalleReq.idProducto()));
            producto.descontarCantidad(detalleReq.cantidadProdcuto());

            DetalleVenta detalle = DetalleVenta.builder()
                    .producto(producto)
                    .cantidadProducto(detalleReq.cantidadProdcuto())
                    .precioProducto(producto.getPrecio())
                    .build();
            venta.agregarDetalle(detalle);
        });
        Venta ventaGuardada = ventaRepository.save(venta);
        log.info("Venta {} registrada exitosamente", ventaGuardada.getId());
        return ventaMapper.entidadAResponse(ventaGuardada);
    }

    @Override
    public void cancelar(Long id) {
        log.info("Cancelando venta con id:  {}", id);

        Venta venta = ventaRepository.findByIdAndEstadoVenta(id, EstadoVenta.REGISTRADA)
                .orElseThrow(()  -> new RecursoNoEncontradoException(
                        "Venta registrada no encontrada con id: " + id));
        venta.getDetalleVenta().forEach(detalle ->
                detalle.getProducto().aumentarCantidad(detalle.getCantidadProducto()));
        venta.cancelar();
        log.info("Venta {} cancelada. stock restaurado", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponse> listarCanceladas() {
        log.info("Listando ventas cambiadas");
        return ventaRepository.findByEstadoVenta(EstadoVenta.CANCELADA)
                .stream().map(ventaMapper::entidadAResponse).toList();
    }
}
