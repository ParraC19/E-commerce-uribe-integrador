package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.ayudas.Departamentos;
import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByUsuario(Usuario usuario);
    Optional<Cliente> findByReferenciaPago(String referenciaPago);
    List<Cliente> findByDepartamento(Departamentos departamento);
    Optional<Cliente> findByCiudad(String ciudad);

}
