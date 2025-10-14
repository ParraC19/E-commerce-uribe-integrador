package com.example.EcomerceUribe.modelos;

import com.example.EcomerceUribe.ayudas.Departamentos;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="direccion",nullable = false, unique = false, length = 250)
    private String direccion;

    @Column(name="calificacion",nullable = false, unique = false, precision = 10, scale = 2)
    private Double calificacion;

    @Column(name="referencia_pago",nullable = false, unique = true, length = 20)
    private String referenciaPago;

    @Enumerated(EnumType.STRING)
    @Column(name="departamento",nullable = false, unique = false)
    private Departamentos departamento;

    @Column(name="ciudad",nullable = false, unique = false, length = 50)
    private String ciudad;

    @OneToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    @JsonManagedReference(value = "relacionclienteusuario")
    private Usuario usuario;

    public Cliente() {
    }

    public Cliente(String direccion, Double calificacion, String referenciaPago, Departamentos departamento, String ciudad) {
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.referenciaPago = referenciaPago;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
