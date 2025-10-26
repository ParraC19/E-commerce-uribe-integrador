package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO.PedidoEspecialDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PedidoServicio {

    @Autowired
    private IPedidoRepository iPedidoRepository;

    @Autowired
    private IPedidoMapa iPedidoMapa;

    public PedidoEspecialDTO guardarPedido(Pedido datosPedido){

        if (datosPedido.getMontoTotal() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Error al procesar el pedido"
            );
        }

        Pedido pedidoGuardado = this.iPedidoRepository.save(datosPedido);
        if (pedidoGuardado == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Error al guardar el pedido"
            );
        }

        return this.iPedidoMapa.pedidoEspecialToDTO(pedidoGuardado);

    }

}
