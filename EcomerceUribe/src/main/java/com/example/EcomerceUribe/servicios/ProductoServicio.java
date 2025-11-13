package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO.ProductoEspecialDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IProductoMapa;
import com.example.EcomerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    //Buscar todos los productos (Lista)
    public List<ProductoEspecialDTO> buscarTodosProductos(){
        List<Producto> productosConsultados=this.productoRepositorio.findAll();
        return this.productoMapa.listaProductoEspecialToDTO(productosConsultados);
    }

    //Buscar un prodcuto por el ID
    public ProductoEspecialDTO buscarProductoEspecialId(Integer id){
        Optional<Producto> productoBuscado=this.productoRepositorio.findById(id);
        if(!productoBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun producto con el id "+id+" suministrado"
            );
        }
        Producto productoEncontrado = productoBuscado.get();
        return this.productoMapa.productoEspecialToDTO(productoEncontrado);
    }

    //eliminar un producto
    public void eliminarProducto(Integer id){
        Optional<Producto> productoBuscado=this.productoRepositorio.findById(id);
        if(!productoBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun producto con el id "+id+" suministrado"
            );
        }
        Producto productoEncontrado = productoBuscado.get();
        try{
            this.productoRepositorio.delete(productoEncontrado);
        }catch(Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el producto, "+error.getMessage()
            );
        }
    }

    //Actualizar producto (precio y aplicaDescuento)
    public ProductoEspecialDTO actualizarProducto(Integer id, Producto datosProducto) {
        Optional<Producto> productoBuscado = this.productoRepositorio.findById(id);
        if (!productoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún producto con el id " + id + " suministrado"
            );
        }

        Producto productoExistente = productoBuscado.get();

        //actualizar precio
        if (datosProducto.getPrecioUnitario() != null && datosProducto.getPrecioUnitario() > 0) {
            productoExistente.setPrecioUnitario(datosProducto.getPrecioUnitario());
        }

        //actualizar si aplica descuento
        productoExistente.setAplicaDescuento(datosProducto.getAplicaDescuento());

        try {
            Producto productoActualizado = this.productoRepositorio.save(productoExistente);
            return this.productoMapa.productoEspecialToDTO(productoActualizado);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el producto: " + e.getMessage()
            );
        }
    }

}
