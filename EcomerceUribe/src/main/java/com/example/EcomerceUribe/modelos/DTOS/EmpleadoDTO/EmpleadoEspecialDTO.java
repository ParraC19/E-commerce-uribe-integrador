package com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;

import com.example.EcomerceUribe.ayudas.Cargos;
import com.example.EcomerceUribe.ayudas.Sedes;

public class EmpleadoEspecialDTO {

    private Cargos cargo;
    private Integer salario;
    private Sedes sede;

    public EmpleadoEspecialDTO() {
    }

    public EmpleadoEspecialDTO(Cargos cargo, Integer salario, Sedes sede) {
        this.cargo = cargo;
        this.salario = salario;
        this.sede = sede;
    }

    public Cargos getCargo() {
        return cargo;
    }

    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public Sedes getSede() {
        return sede;
    }

    public void setSede(Sedes sede) {
        this.sede = sede;
    }
}
