package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO.ClienteEspecialDTO;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

}
