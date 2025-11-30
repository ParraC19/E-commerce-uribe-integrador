package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO.EmpleadoEspecialDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio {

    @Autowired
    private IEmpleadoRepositorio iEmpleadoRepositorio;

    @Autowired
    private IEmpleadoMapa iEmpleadoMapa;

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

        Empleado empleadoGuardado = this.iEmpleadoRepositorio.save(datosEmpleado);
        if(empleadoGuardado==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error al guardar el empleado"
            );
        }

        return this.iEmpleadoMapa.empleadoEspecialToDTO(empleadoGuardado);

    }

    public List<EmpleadoEspecialDTO> buscarTodosEmpleados(){
        List<Empleado> empladosConsultados=this.iEmpleadoRepositorio.findAll();
        return this.iEmpleadoMapa.listaEmpleadoEspecialToDTO(empladosConsultados);
    }

    public EmpleadoEspecialDTO buscarEmpleadoEspecialId(Integer id){
        Optional<Empleado> empleadoBuscado=this.iEmpleadoRepositorio.findById(id);
        if(!empleadoBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun empleado con el id "+id+" suministrado"
            );
        }
        Empleado empleadoEncontrado = empleadoBuscado.get();
        return this.iEmpleadoMapa.empleadoEspecialToDTO(empleadoEncontrado);
    }

    public void eliminarEmpleado(Integer id){
        Optional<Empleado> empleadoBuscado=this.iEmpleadoRepositorio.findById(id);
        if(!empleadoBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun empleado con el id "+id+" suministrado"
            );
        }
        Empleado empleadoEncontrado = empleadoBuscado.get();
        try{
            this.iEmpleadoRepositorio.delete(empleadoEncontrado);
        }catch(Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el empleado, "+error.getMessage()
            );
        }
    }

    public EmpleadoEspecialDTO actualizarEmpleado(Integer id, Empleado datosEmpleado) {
        Optional<Empleado> empleadoBuscado = this.iEmpleadoRepositorio.findById(id);
        if (!empleadoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún empleado con el id " + id + " suministrado"
            );
        }

        Empleado empleadoEncontrado = empleadoBuscado.get();

        if (empleadoEncontrado.getUsuario() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El empleado no está vinculado a ningún usuario. No se puede actualizar."
            );
        }

            empleadoEncontrado.setCargo(datosEmpleado.getCargo());
            empleadoEncontrado.setSalario(datosEmpleado.getSalario());
            empleadoEncontrado.setSede(datosEmpleado.getSede());


            Empleado empleadoActualizado = this.iEmpleadoRepositorio.save(empleadoEncontrado);

        if (empleadoActualizado == null){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el empleado con el id: " + id
            );
        }
        return this.iEmpleadoMapa.empleadoEspecialToDTO(empleadoActualizado);
    }

}
