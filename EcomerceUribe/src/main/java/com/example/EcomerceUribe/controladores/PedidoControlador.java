package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO.PedidoEspecialDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.servicios.PedidoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name="Controlador para operaciones en la tabla pedidos")
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    // Crear pedido
    @Operation(summary = "Crear un pedido en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<PedidoEspecialDTO> guardarPedido(@RequestBody Pedido datos) {
        PedidoEspecialDTO respuesta = this.pedidoServicio.guardarPedido(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    // Listar todos los pedidos
    @Operation(summary = "Listar todos los pedidos guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PedidoEspecialDTO>> listarPedido() {
        List<PedidoEspecialDTO> respuesta = this.pedidoServicio.buscarTodosPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Buscar por ID
    @Operation(summary = "Buscar un pedido en la BD por ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoEspecialDTO> buscarPorIdPedido(@PathVariable Integer id) {
        PedidoEspecialDTO respuesta = this.pedidoServicio.buscarPedidoEspecialId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Eliminar
    @Operation(summary = "Eliminar un pedido de la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Integer id) {
        this.pedidoServicio.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    // Modificar
    @Operation(summary = "Modificar monto total, costo de envío y fecha de entrega de un pedido en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoEspecialDTO> modificarPedido(@PathVariable Integer id, @RequestBody Pedido datos) {
        PedidoEspecialDTO respuesta = this.pedidoServicio.actualizarPedido(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}