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
    private IClienteRepositorio iClienteRepositorio;

    @Autowired
    private IClienteMapa iClienteMapa;

    public ClienteEspecialDTO guardarCliente(Cliente datosCliente){

        if (datosCliente.getDireccion()== null || datosCliente.getDireccion().isBlank()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Direccion requerido para el usuario"
            );
        }

       Cliente clienteGuardado = this.iClienteRepositorio.save(datosCliente);
        if (clienteGuardado == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error al guardar el cliente"
            );
        }

        return this.iClienteMapa.clienteEspecialToDTO(clienteGuardado);

    }

    public List<ClienteEspecialDTO> buscarTodosClientes(){
        List<Cliente> clientesConsultados=this.iClienteRepositorio.findAll();
        return this.iClienteMapa.listaClienteEspecialToDTO(clientesConsultados);
    }

    public ClienteEspecialDTO buscarClienteId(Integer id){
        Optional<Cliente> clienteBuscado =this.iClienteRepositorio.findById(id);
        if(!clienteBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun cliente con el id "+id+" suministrado"
            );
        }
        Cliente clienteEncontrado = clienteBuscado.get();
        return this.iClienteMapa.clienteEspecialToDTO(clienteEncontrado);
    }

    public void eliminarCliente(Integer id){
        Optional<Cliente> clienteBuscado=this.iClienteRepositorio.findById(id);
        if(!clienteBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun cliente con el id "+id+" suministrado"
            );
        }
        Cliente clienteEncontrado = clienteBuscado.get();
        try{
            this.iClienteRepositorio.delete(clienteEncontrado);
        }catch(Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el cliente, "+error.getMessage()
            );
        }
    }

    public ClienteEspecialDTO actualizarCliente(Integer id, Cliente datosCliente) {
        Optional<Cliente> clienteBuscado = this.iClienteRepositorio.findById(id);
        if (!clienteBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún cliente con el id " + id + " suministrado"
            );
        }

        Cliente clienteExistente = clienteBuscado.get();
        if (datosCliente.getReferenciaPago() != null && datosCliente.getReferenciaPago().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La referencia de pago no puede estar vacía"
            );
        }

            clienteExistente.setDireccion(datosCliente.getDireccion());
            clienteExistente.setCalificacion(datosCliente.getCalificacion());
            clienteExistente.setCiudad(datosCliente.getCiudad());



            Cliente clienteActualizado = this.iClienteRepositorio.save(clienteExistente);

        if (clienteActualizado == null){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el cliente"
            );
        }
        return this.iClienteMapa.clienteEspecialToDTO(clienteActualizado);
    }

}
