package com.santiago.almacen.controller;

import com.santiago.almacen.dto.sucursales.SucursalRequest;
import com.santiago.almacen.dto.sucursales.SucursalResponse;
import com.santiago.almacen.services.SucursalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class SucursalController {
    private  final SucursalService sucursalService;

    public ResponseEntity<List<SucursalResponse>> listar(){
        return ResponseEntity.ok(sucursalService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SucursalResponse> obtenerPorId
            (@PathVariable @Positive(message = "El ID debe ser positivo") Long id){
        return ResponseEntity.ok(sucursalService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<SucursalResponse> registrar(@Valid @RequestBody SucursalRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.registrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalResponse> actualizar(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id,
            @Valid @RequestBody SucursalRequest request) {
            return ResponseEntity.ok(sucursalService.actualizar(request, id));
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id) {
        sucursalService.eliminar(id);
        return ResponseEntity.noContent().build();

    }

}







