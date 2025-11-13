package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO.ClienteEspecialDTO;
import com.example.EcomerceUribe.servicios.ClienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Controlador para operaciones en la tabla clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    // Crear cliente
    @Operation(summary = "Crear un cliente en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ClienteEspecialDTO> guardarCliente(@RequestBody Cliente datos) {
        ClienteEspecialDTO respuesta = this.clienteServicio.guardarCliente(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    // Listar todos
    @Operation(summary = "Listar todos los clientes guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ClienteEspecialDTO>> listarClientes() {
        List<ClienteEspecialDTO> respuesta = this.clienteServicio.buscarTodosClientes();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Buscar por ID
    @Operation(summary = "Buscar un cliente en la BD por su ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteEspecialDTO> buscarPorIdEmpleado(@PathVariable Integer id) {
        ClienteEspecialDTO respuesta = this.clienteServicio.buscarClienteId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Eliminar
    @Operation(summary = "Eliminar un cliente en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Integer id) {
        this.clienteServicio.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    // Modificar
    @Operation(summary = "Modificar la información de un cliente en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteEspecialDTO> modificarEmpleado(@PathVariable Integer id, @RequestBody Cliente datos) {
        ClienteEspecialDTO respuesta = this.clienteServicio.actualizarCliente(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}