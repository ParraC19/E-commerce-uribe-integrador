package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioDTO.UsuarioEspecialDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping
    public UsuarioEspecialDTO guardarUsuario(@RequestBody Usuario usuarioAGuardar){
        return usuarioServicio.guardarUsuarioEspecial(usuarioAGuardar);
    }

}
