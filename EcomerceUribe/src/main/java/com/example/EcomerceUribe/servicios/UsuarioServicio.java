package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioDTO.UsuarioEspecialDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.EcomerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IUsuarioMapa iUsuarioMapa;

    public UsuarioEspecialDTO guardarUsuarioEspecial(Usuario datosUsuario){

        if (this.usuarioRepositorio.findByCorreo(datosUsuario.getCorreo()).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,"Ya existe un usuario con el correo ingresado"
            );
        }

        if (datosUsuario.getNombres()==null || datosUsuario.getNombres().isBlank()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"El nombre del usuario no puede estar vacio"
            );
        }

        if (datosUsuario.getContraseña().length()<8){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"La contraseña deben tener minimo 8 caracteres"
            );
        }

        Usuario usuarioGuardado = this.usuarioRepositorio.save(datosUsuario);
        if (usuarioGuardado==null){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el usuario"
            );
        }

        return this.iUsuarioMapa.convertir_usuario_a_usuarioespecialdto(usuarioGuardado);

    }

}
