package com.santiago.almacen.repositories;


import com.santiago.almacen.entities.Venta;
import com.santiago.almacen.enums.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByEstadoVenta(EstadoVenta estado);

    Optional<Venta> findByIdAndEstadoVenta(Long id, EstadoVenta estado);

    boolean existsBySucursalIdAndEstadoVenta(Long sucursalId, EstadoVenta estado);
    boolean existsByDetalleVentaProductoIdAndEstadoVenta(Long productoId, EstadoVenta estado);

    Long id(long id);
}
