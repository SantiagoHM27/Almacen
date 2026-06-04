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
    @PostMapping
    public ResponseEntity.ok<ventaService> registrar(@Valid @RequestBody VentaRequest request){
        return ResponseEntity.status((HttpStatus.CREATED).body(ventaService.registrar(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<void>



}
