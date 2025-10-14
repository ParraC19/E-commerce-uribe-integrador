package com.example.EcomerceUribe.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="montoTotal",nullable = false, unique = false, length = 10)
    private Integer montoTotal;
    @Column(name="fecha_creacion",nullable = false)
    private LocalDate fechaCreacion;
    @Column(name="fecha_entrega",nullable = false)
    private LocalDate fechaEntrega;
    @Column(name = "costro_envio", nullable = false, unique = false, length = 10)
    private Integer costoEnvio;

    @OneToMany(mappedBy = "pedido")
    @JsonManagedReference(value = "relacionpedidoproducto")
    private List<Producto> productos;

    public Pedido() {
    }

    public Pedido(Integer montoTotal, LocalDate fechaCreacion, LocalDate fechaEntrega, Integer costoEnvio) {
        this.montoTotal = montoTotal;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.costoEnvio = costoEnvio;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(Integer costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public Pedido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
