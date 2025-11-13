package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO.ClienteEspecialDTO;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private IClienteRepositorio clienteRepositorio;

    @Autowired
    private IClienteMapa iClienteMapa;

    public ClienteEspecialDTO guardarCliente(Cliente datosCliente){

        if (datosCliente.getDireccion()== null || datosCliente.getDireccion().isBlank()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Direccion requerido para el usuario"
            );
        }

       Cliente clienteGuardado = this.clienteRepositorio.save(datosCliente);
        if (clienteGuardado == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error al guardar el cliente"
            );
        }

        return this.iClienteMapa.clienteEspecialToDTO(clienteGuardado);

    }

    //Buscar todos los clientes (Lista)
    public List<ClienteEspecialDTO> buscarTodosClientes(){
        List<Cliente> clientesConsultados=this.clienteRepositorio.findAll();
        return this.iClienteMapa.listaClienteEspecialToDTO(clientesConsultados);
    }

    //Buscar un cliente por el ID
    public ClienteEspecialDTO buscarClienteId(Integer id){
        Optional<Cliente> clienteBuscado =this.clienteRepositorio.findById(id);
        if(!clienteBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun cliente con el id "+id+" suministrado"
            );
        }
        Cliente clienteEncontrado = clienteBuscado.get();
        return this.iClienteMapa.clienteEspecialToDTO(clienteEncontrado);
    }

    //eliminar un cliente
    public void eliminarCliente(Integer id){
        Optional<Cliente> clienteBuscado=this.clienteRepositorio.findById(id);
        if(!clienteBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun cliente con el id "+id+" suministrado"
            );
        }
        Cliente clienteEncontrado = clienteBuscado.get();
        try{
            this.clienteRepositorio.delete(clienteEncontrado);
        }catch(Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el cliente, "+error.getMessage()
            );
        }
    }

    // actualizar un cliente (direccion, calificacion, referenciaPago, departamento, ciudad)
    public ClienteEspecialDTO actualizarCliente(Integer id, Cliente datosCliente) {
        Optional<Cliente> clienteBuscado = this.clienteRepositorio.findById(id);

        // Validar que el cliente exista
        if (!clienteBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún cliente con el id " + id + " suministrado"
            );
        }

        Cliente clienteExistente = clienteBuscado.get();

        // validar si se intenta actualizar con una referencia de pago vacía
        if (datosCliente.getReferenciaPago() != null && datosCliente.getReferenciaPago().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La referencia de pago no puede estar vacía"
            );
        }

        //actualizar solo los campos permitidos
        if (datosCliente.getDireccion() != null) {
            clienteExistente.setDireccion(datosCliente.getDireccion());
        }

        if (datosCliente.getCalificacion() != null && datosCliente.getCalificacion() >= 0) {
            clienteExistente.setCalificacion(datosCliente.getCalificacion());
        }

        if (datosCliente.getReferenciaPago() != null) {
            clienteExistente.setReferenciaPago(datosCliente.getReferenciaPago());
        }

        if (datosCliente.getDepartamento() != null) {
            clienteExistente.setDepartamento(datosCliente.getDepartamento());
        }

        if (datosCliente.getCiudad() != null) {
            clienteExistente.setCiudad(datosCliente.getCiudad());
        }

        try {
            Cliente clienteActualizado = this.clienteRepositorio.save(clienteExistente);
            return this.iClienteMapa.clienteEspecialToDTO(clienteActualizado);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el cliente: " + e.getMessage()
            );
        }
    }

}
