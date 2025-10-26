package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO.EmpleadoEspecialDTO;
import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO.EmpleadoGenericoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEmpleadoMapa {

    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "sede", target = "sede")
    EmpleadoGenericoDTO empleadoToDTO(Empleado empleado);

    List<EmpleadoGenericoDTO> listaEmpleadoToDTO(List<Empleado> listaEmpleado);

    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "salario", target = "salario")
    @Mapping(source = "sede", target = "sede")
    EmpleadoEspecialDTO empleadoEspecialToDTO(Empleado empleado);

    List<EmpleadoEspecialDTO> listaEmpleadoEspecialToDTO(List<Empleado> listaEmpleado);

}