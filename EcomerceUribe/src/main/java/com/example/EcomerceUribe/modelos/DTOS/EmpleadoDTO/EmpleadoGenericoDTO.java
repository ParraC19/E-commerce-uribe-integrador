package com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;

import com.example.EcomerceUribe.ayudas.Cargos;
import com.example.EcomerceUribe.ayudas.Sedes;

public class EmpleadoGenericoDTO {
    private Cargos cargo;
    private Sedes sede;

    public EmpleadoGenericoDTO() {
    }

    public EmpleadoGenericoDTO(Cargos cargo, Sedes sede) {
        this.cargo = cargo;
        this.sede = sede;
    }

    public Cargos getCargo() {
        return cargo;
    }

    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }

    public Sedes getSede() {
        return sede;
    }

    public void setSede(Sedes sede) {
        this.sede = sede;
    }
}
