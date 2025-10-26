package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO.ClienteEspecialDTO;
import com.example.EcomerceUribe.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    public ClienteEspecialDTO guardarCliente(@RequestBody Cliente clienteAGuardar){
        return clienteServicio.guardarCliente(clienteAGuardar);
    }

}
