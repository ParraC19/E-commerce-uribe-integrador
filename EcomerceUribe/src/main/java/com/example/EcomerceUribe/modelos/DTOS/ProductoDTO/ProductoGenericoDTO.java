package com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;

import com.example.EcomerceUribe.ayudas.Categorias;

public class ProductoGenericoDTO {
    private String nombre;
    private String descripcion;
    private String fotografia;
    private Categorias categoria;
    private Integer precioUnitario;
    private String marca;

    public ProductoGenericoDTO() {
    }

    public ProductoGenericoDTO(String nombre, String descripcion, String fotografia, Categorias categoria, Integer precioUnitario, String marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotografia = fotografia;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.marca = marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
