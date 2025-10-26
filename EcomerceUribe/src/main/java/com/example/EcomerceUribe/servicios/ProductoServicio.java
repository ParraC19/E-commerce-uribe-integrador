package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO.ProductoEspecialDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IProductoMapa;
import com.example.EcomerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductoServicio {

    @Autowired
    private IProductoRepositorio productoRepositorio;

    @Autowired
    private IProductoMapa productoMapa;

    public ProductoEspecialDTO guardarProducto(Producto datosProducto){

        if (datosProducto.getNombre() == null || datosProducto.getDescripcion() == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"La informacion del producto debe estar completa"
            );
        }

        Producto productoGuardado = this.productoRepositorio.save(datosProducto);
        if (productoGuardado == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Error al guardar el producto"
            );
        }

        return this.productoMapa.productoEspecialToDTO(productoGuardado);

    }

}
