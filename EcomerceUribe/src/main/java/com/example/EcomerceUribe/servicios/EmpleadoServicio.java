package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO.EmpleadoEspecialDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmpleadoServicio {

    @Autowired
    private IEmpleadoRepositorio empleadoRepositorio;

    @Autowired
    private IEmpleadoMapa empleadoMapa;

    public EmpleadoEspecialDTO guardarEmpleado(Empleado datosEmpleado){

        if (datosEmpleado.getSede()==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Debe ingresar la sede del empleado"
            );
        }

        if (datosEmpleado.getCargo()==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Debe ingresar el cargo del empleado"
            );
        }

        Empleado empleadoGuardado = this.empleadoRepositorio.save(datosEmpleado);
        if(empleadoGuardado==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error al guardar el empleado"
            );
        }

        return this.empleadoMapa.empleadoEspecialToDTO(empleadoGuardado);

    }

}
