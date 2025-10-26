package com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;

import java.time.LocalDate;

public class PedidoGenericoDTO {
    private Integer montoTotal;
    private LocalDate fechaCreacion;

    public PedidoGenericoDTO() {
    }

    public PedidoGenericoDTO(Integer montoTotal, LocalDate fechaCreacion) {
        this.montoTotal = montoTotal;
        this.fechaCreacion = fechaCreacion;
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
}
