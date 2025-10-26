package com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;

public class ClienteEspecialDTO {
    private String direccion;
    private String referenciaPago;
    private String calificacion;
    private String ciudad;

    public ClienteEspecialDTO() {
    }

    public ClienteEspecialDTO(String direccion, String referenciaPago, String calificacion, String ciudad) {
        this.direccion = direccion;
        this.referenciaPago = referenciaPago;
        this.calificacion = calificacion;
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
