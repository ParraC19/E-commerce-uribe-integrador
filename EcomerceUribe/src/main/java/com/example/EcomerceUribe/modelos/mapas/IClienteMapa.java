package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO.ClienteEspecialDTO;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO.ClienteGenericoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClienteMapa {

    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "calificacion", target = "calificacion")
    @Mapping(source = "ciudad", target = "ciudad")
    ClienteGenericoDTO clienteGenericoToDTO(Cliente cliente);

    List<ClienteGenericoDTO> listaClienteGenericoToDTO(List<Cliente> listaCliente);

    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "referenciaPago", target = "referenciaPago")
    @Mapping(source = "ciudad", target = "ciudad")
    ClienteEspecialDTO clienteEspecialToDTO(Cliente cliente);

    List<ClienteEspecialDTO> listaClienteEspecialToDTO(List<Cliente> listaCliente);


}
