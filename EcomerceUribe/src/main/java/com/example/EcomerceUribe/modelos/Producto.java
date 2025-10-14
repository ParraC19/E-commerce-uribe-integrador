package com.example.EcomerceUribe.modelos;

import com.example.EcomerceUribe.ayudas.Categorias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nombre",nullable = false, unique = false, length = 70)
    private String nombre;
    @Column(name="fotografia",nullable = false, unique = false, length = 100)
    private String fotografia;
    @Column(name="descripcion",nullable = false, unique = false, length = 250)
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column(name="categoria",nullable = false, unique = false)
    private Categorias categoria;
    @Column(name="precio_unitario",nullable = false, unique = false, length = 10)
    private Integer precioUnitario;
    @Column(name="marca",nullable = false, unique = false, length = 50)
    private String marca;
    @Column(name="descuento",nullable = false)
    private Boolean aplicaDescuento;

    @ManyToOne
    @JoinColumn(name = "fk_pedido", referencedColumnName = "id")
    @JsonBackReference(value = "relacionpedidoproducto")
    private Pedido pedido;

    public Producto() {
    }

    public Producto(String nombre, String fotografia, String descripcion, Categorias categoria, Integer precioUnitario, String marca, Boolean aplicaDescuento) {
        this.nombre = nombre;
        this.fotografia = fotografia;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.marca = marca;
        this.aplicaDescuento = aplicaDescuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Boolean getAplicaDescuento() {
        return aplicaDescuento;
    }

    public void setAplicaDescuento(Boolean aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
