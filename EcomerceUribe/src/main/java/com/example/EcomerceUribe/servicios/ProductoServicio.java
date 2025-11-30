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
    private IProductoRepositorio iProductoRepositorio;

    @Autowired
    private IProductoMapa iProductoMapa;

    public ProductoEspecialDTO guardarProducto(Producto datosProducto){

        if (datosProducto.getNombre() == null || datosProducto.getDescripcion() == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"La informacion del producto debe estar completa"
            );
        }

        Producto productoGuardado = this.iProductoRepositorio.save(datosProducto);
        if (productoGuardado == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Error al guardar el producto"
            );
        }

        return this.iProductoMapa.productoEspecialToDTO(productoGuardado);

    }

    public List<ProductoEspecialDTO> buscarTodosProductos(){
        List<Producto> productosConsultados=this.iProductoRepositorio.findAll();
        return this.iProductoMapa.listaProductoEspecialToDTO(productosConsultados);
    }

    public ProductoEspecialDTO buscarProductoEspecialId(Integer id){
        Optional<Producto> productoBuscado=this.iProductoRepositorio.findById(id);
        if(!productoBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun producto con el id "+id+" suministrado"
            );
        }
        Producto productoEncontrado = productoBuscado.get();
        return this.iProductoMapa.productoEspecialToDTO(productoEncontrado);
    }

    public void eliminarProducto(Integer id){
        Optional<Producto> productoBuscado=this.iProductoRepositorio.findById(id);
        if(!productoBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun producto con el id "+id+" suministrado"
            );
        }
        Producto productoEncontrado = productoBuscado.get();
        try{
            this.iProductoRepositorio.delete(productoEncontrado);
        }catch(Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el producto, "+error.getMessage()
            );
        }
    }

    public ProductoEspecialDTO actualizarProducto(Integer id, Producto datosProducto) {
        Optional<Producto> productoBuscado = this.iProductoRepositorio.findById(id);
        if (!productoBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró ningún producto con el id " + id + " suministrado"
            );
        }

        Producto productoExistente = productoBuscado.get();

        productoExistente.setPrecioUnitario(datosProducto.getPrecioUnitario());
        productoExistente.setAplicaDescuento(datosProducto.getAplicaDescuento());


            Producto productoActualizado = this.iProductoRepositorio.save(productoExistente);

        if (productoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el producto: "
            );
        }
        return this.iProductoMapa.productoEspecialToDTO(productoActualizado);
    }

}
