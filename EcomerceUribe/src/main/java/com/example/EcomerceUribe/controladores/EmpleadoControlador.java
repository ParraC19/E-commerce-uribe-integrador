package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO.EmpleadoEspecialDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.servicios.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empleados")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    public EmpleadoEspecialDTO guardarEmpleado(@RequestBody Empleado empleadoAGuardar){
        return empleadoServicio.guardarEmpleado(empleadoAGuardar);
    }

}
