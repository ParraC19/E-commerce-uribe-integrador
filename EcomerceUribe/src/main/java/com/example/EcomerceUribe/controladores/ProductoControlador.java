package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO.ProductoEspecialDTO;import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.servicios.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Controlador para operaciones en la tabla productos")
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;

    // Crear producto
    @Operation(summary = "Crear un producto en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ProductoEspecialDTO> guardarProducto(@RequestBody Producto datos) {
        ProductoEspecialDTO respuesta = this.productoServicio.guardarProducto(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    // Listar todos
    @Operation(summary = "Listar todos los productos guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProductoEspecialDTO>> listarProductos() {
        List<ProductoEspecialDTO> respuesta = this.productoServicio.buscarTodosProductos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Buscar por ID
    @Operation(summary = "Buscar un producto en la BD por su ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoEspecialDTO> buscarPorIdProducto(@PathVariable Integer id) {
        ProductoEspecialDTO respuesta = this.productoServicio.buscarProductoEspecialId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Eliminar
    @Operation(summary = "Eliminar un producto en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        this.productoServicio.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // Modificar
    @Operation(summary = "Modificar el precio y descuento de un producto en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoEspecialDTO> modificarProducto(@PathVariable Integer id, @RequestBody Producto datos) {
        ProductoEspecialDTO respuesta = this.productoServicio.actualizarProducto(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}