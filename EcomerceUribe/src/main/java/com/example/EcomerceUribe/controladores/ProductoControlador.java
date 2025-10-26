package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO.ProductoEspecialDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    public ProductoEspecialDTO guardarProducto(@RequestBody Producto productoAGuardar){
        return productoServicio.guardarProducto(productoAGuardar);
    }
}
