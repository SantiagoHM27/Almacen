package com.santiago.almacen.repositories;

import com.santiago.almacen.dto.ventas.VentaResponse;
import com.santiago.almacen.entities.Venta;
import com.santiago.almacen.enums.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findEstadoVenta(EstadoVenta estado);

    Optional<Venta> findIdAndEstadoVenta(Long id, EstadoVenta estado);

    boolean existsBySucursalIdAndEstadoVenta(Long sucursalId, EstadoVenta estado);
    boolean existsByDetalleVentaProductoIdAndEstadoVenta(Long productoId, EstadoVenta estado);

    List<VentaResponse> findByEstadoVenta(EstadoVenta estadoVenta);

    Long id(long id);
}
