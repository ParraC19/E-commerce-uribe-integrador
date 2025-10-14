package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.ayudas.Cargos;
import com.example.EcomerceUribe.ayudas.Sedes;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
    List<Empleado> findByCargo(Cargos cargo);
    Optional<Empleado> findeBtUsuario(Usuario usuario);
    List<Empleado> findbySede (Sedes sede);
}
