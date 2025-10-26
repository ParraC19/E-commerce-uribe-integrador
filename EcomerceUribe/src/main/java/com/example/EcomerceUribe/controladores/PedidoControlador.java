package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO.PedidoEspecialDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.servicios.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    public PedidoEspecialDTO guardarPedido(@RequestBody Pedido pedidoAGuardar){
        return pedidoServicio.guardarPedido(pedidoAGuardar);
    }

}
