package com.santiago.almacen.controller;


import com.santiago.almacen.dto.ventas.VentaRequest;
import com.santiago.almacen.dto.ventas.VentaResponse;
import com.santiago.almacen.services.VentaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@AllArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaResponse>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

    @PostMapping("/{id}")
    public ResponseEntity<VentaResponse> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(ventaService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id){
        ventaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/canceladas")
    public ResponseEntity<List<VentaResponse>> listarCanceladas(){
        return ResponseEntity.ok(ventaService.listarCanceladas());
    }



}
