package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO.ProductoEspecialDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO.ProductoGenericoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoMapa {

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "fotografia", target = "fotografia")
    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "precioUnitario", target = "precioUnitario")
    @Mapping(source = "marca", target = "marca")
    ProductoGenericoDTO productoGenericoToDTO(Producto producto);
    List<Producto> listaProductoGenericoToDTO(List<Producto> listaProducto);

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "fotografia", target = "fotografia")
    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "precioUnitario", target = "precioUnitario")
    @Mapping(source = "marca", target = "marca")
    @Mapping(source = "aplicaDescuento", target = "aplicaDescuento")
    ProductoEspecialDTO productoEspecialToDTO(Producto producto);
    List<Producto> listaProductoEspecialToDTO(List<Producto> listaProducto);

}
