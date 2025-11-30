package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO.PedidoEspecialDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public List<PedidoEspecialDTO> buscarTodosPedidos() {
        List<Pedido> listaDePedidosConsultados = this.iPedidoRepository.findAll();
        return this.iPedidoMapa.listaPedidoEspecialToDTO(listaDePedidosConsultados);
    }

    public PedidoEspecialDTO buscarPedidoEspecialId(Integer id) {
        Optional<Pedido> pedidoBuscado = this.iPedidoRepository.findById(id);
        if (!pedidoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun pedido con el id " + id + " suministrado"
            );
        }
        Pedido pedidoEncontrado = pedidoBuscado.get();
        return this.iPedidoMapa.pedidoEspecialToDTO(pedidoEncontrado);
    }

    public void eliminarPedido(Integer id) {
        Optional<Pedido> pedidoQueEstoyBuscando = this.iPedidoRepository.findById(id);
        if (!pedidoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun pedido con el id " + id + " suministrado"
            );
        }
        Pedido pedidoEncontrado = pedidoQueEstoyBuscando.get();
        try {
            this.iPedidoRepository.delete(pedidoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el pedido, " + error.getMessage()
            );
        }
    }

    public PedidoEspecialDTO actualizarPedido(Integer id, Pedido datosPedido) {
        Optional<Pedido> pedidoBuscado = this.iPedidoRepository.findById(id);
        if (!pedidoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún pedido con el id " + id + " suministrado"
            );
        }

        Pedido pedidoExistente = pedidoBuscado.get();

        pedidoExistente.setFechaEntrega(datosPedido.getFechaEntrega());
        pedidoExistente.setMontoTotal(datosPedido.getMontoTotal());
        pedidoExistente.setCostoEnvio(datosPedido.getCostoEnvio());

        Pedido pedidoActualizado = this.iPedidoRepository.save(pedidoExistente);

        if (pedidoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,"Error al actualizar el usuario"
                    );
        }

        return this.iPedidoMapa.pedidoEspecialToDTO(pedidoActualizado);
    }

}
