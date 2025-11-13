package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO.EmpleadoEspecialDTO;
import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO.EmpleadoGenericoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.servicios.EmpleadoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Controlador para operaciones en la tabla empleados")
public class EmpleadoControlador {

    @Autowired
    EmpleadoServicio empleadoServicio;

    // Crear empleado
    @Operation(summary = "Crear un empleado en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<EmpleadoEspecialDTO> guardarEmpleado(@RequestBody Empleado datos) {
        EmpleadoEspecialDTO respuesta = this.empleadoServicio.guardarEmpleado(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    // Listar todos
    @Operation(summary = "Listar todos los empleados guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EmpleadoEspecialDTO>> buscarEmplados() {
        List<EmpleadoEspecialDTO> respuesta = this.empleadoServicio.buscarTodosEmpleados();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Buscar por ID
    @Operation(summary = "Buscar un empleado en la BD por su ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoEspecialDTO> buscarPorIdEmplado(@PathVariable Integer id) {
        EmpleadoEspecialDTO respuesta = this.empleadoServicio.buscarEmpleadoEspecialId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    // Eliminar
    @Operation(summary = "Eliminar un empleado en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminarEmpleaado(@PathVariable Integer id) {
        this.empleadoServicio.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    // Modificar
    @Operation(summary = "Modificar el salario, cargo o sede de un empleado en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoEspecialDTO> modificarEmpleado(@PathVariable Integer id, @RequestBody Empleado datos) {
        EmpleadoEspecialDTO respuesta = this.empleadoServicio.actualizarEmpleado(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}