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

    //Buscar todos los empleados (Lista)
    public List<EmpleadoEspecialDTO> buscarTodosEmpleados(){
        List<Empleado> empladosConsultados=this.empleadoRepositorio.findAll();
        return this.empleadoMapa.listaEmpleadoEspecialToDTO(empladosConsultados);
    }

    //Buscar un empleado por el ID
    public EmpleadoEspecialDTO buscarEmpleadoEspecialId(Integer id){
        Optional<Empleado> empleadoBuscado=this.empleadoRepositorio.findById(id);
        if(!empleadoBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun empleado con el id "+id+" suministrado"
            );
        }
        Empleado empleadoEncontrado = empleadoBuscado.get();
        return this.empleadoMapa.empleadoEspecialToDTO(empleadoEncontrado);
    }

    //eliminar un empleado
    public void eliminarEmpleado(Integer id){
        Optional<Empleado> empleadoBuscado=this.empleadoRepositorio.findById(id);
        if(!empleadoBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun empleado con el id "+id+" suministrado"
            );
        }
        Empleado empleadoEncontrado = empleadoBuscado.get();
        try{
            this.empleadoRepositorio.delete(empleadoEncontrado);
        }catch(Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el empleado, "+error.getMessage()
            );
        }
    }

    // Actualizar un empleado (cargo, salario, sede)
    public EmpleadoEspecialDTO actualizarEmpleado(Integer id, Empleado datosEmpleado) {
        Optional<Empleado> empleadoBuscado = this.empleadoRepositorio.findById(id);
        if (!empleadoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún empleado con el id " + id + " suministrado"
            );
        }

        Empleado empleadoExistente = empleadoBuscado.get();

        //Validación: verificar si el empleado está vinculado a un usuario
        if (empleadoExistente.getUsuario() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El empleado no está vinculado a ningún usuario. No se puede actualizar."
            );
        }

        //actualizar los campos permitidos
        if (datosEmpleado.getCargo() != null) {
            empleadoExistente.setCargo(datosEmpleado.getCargo());
        }

        if (datosEmpleado.getSalario() != null && datosEmpleado.getSalario() > 0) {
            empleadoExistente.setSalario(datosEmpleado.getSalario());
        }

        if (datosEmpleado.getSede() != null) {
            empleadoExistente.setSede(datosEmpleado.getSede());
        }

        try {
            Empleado empleadoActualizado = this.empleadoRepositorio.save(empleadoExistente);
            return this.empleadoMapa.empleadoEspecialToDTO(empleadoActualizado);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el empleado: " + e.getMessage()
            );
        }
    }

}
