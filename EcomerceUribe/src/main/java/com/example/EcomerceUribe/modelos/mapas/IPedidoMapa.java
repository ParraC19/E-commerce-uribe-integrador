package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO.PedidoEspecialDTO;
import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO.PedidoGenericoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPedidoMapa {

    @Mapping(source = "montoTotal", target = "montoTotal")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    PedidoGenericoDTO pedidoGenericoToDTO(Pedido pedido);

    List<PedidoGenericoDTO> listaPedidoGenericoToDTO(List<Pedido> pedido);

    @Mapping(source = "montoTotal", target = "montoTotal")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    @Mapping(source = "fechaEntrega", target = "fechaEntrega")
    @Mapping(source = "costoEnvio", target = "costoEnvio")
    PedidoEspecialDTO pedidoEspecialToDTO(Pedido pedido);

    List<PedidoEspecialDTO> listaPedidoEspecialToDTO(List<Pedido> listaProducto);


}
