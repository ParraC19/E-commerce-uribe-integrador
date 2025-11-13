package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioDTO.UsuarioEspecialDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioDTO.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.EcomerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IUsuarioMapa iUsuarioMapa;

    public UsuarioEspecialDTO guardarUsuarioEspecial(Usuario datosUsuario) {

        if (this.usuarioRepositorio.findByCorreo(datosUsuario.getCorreo()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existe un usuario con el correo ingresado"
            );
        }

        if (datosUsuario.getNombres() == null || datosUsuario.getNombres().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El nombre del usuario no puede estar vacio"
            );
        }

        if (datosUsuario.getContraseña().length() < 8) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La contraseña deben tener minimo 8 caracteres"
            );
        }

        Usuario usuarioGuardado = this.usuarioRepositorio.save(datosUsuario);
        if (usuarioGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el usuario"
            );
        }

        return this.iUsuarioMapa.convertir_usuario_a_usuarioespecialdto(usuarioGuardado);
    }

    public List<UsuarioGenericoDTO> buscarTodosUsuarios() {

        List<Usuario> listadConsultados = this.usuarioRepositorio.findAll();

        return this.iUsuarioMapa.convertir_lista_a_listadtogenerico(listadConsultados);
    }

    ;

    public UsuarioGenericoDTO buscarUsuarioId(Integer id) {
        Optional<Usuario> usuarioBuscado = this.usuarioRepositorio.findById(id);

        if (!usuarioBuscado.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No se encontro el usuario buscado por el id:" + id + "suministrado"
            );
        }

        Usuario usuarioEncontrado = usuarioBuscado.get();
        return this.iUsuarioMapa.convertir_usuario_a_usuariogenericodto(usuarioEncontrado);
    }

    ;

    public void eliminarUsuario(Integer id) {
        Optional<Usuario> usuarioBuscado = this.usuarioRepositorio.findById(id);

        if ((!usuarioBuscado.isPresent())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No se encontro el usuario buscado por el id:" + id + "suministrado"
            );
        }

        Usuario usuarioEncontrado = usuarioBuscado.get();
        try {
            this.usuarioRepositorio.delete(usuarioEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el usuario");
        }
    }

    public UsuarioGenericoDTO actualizarUsuario(Integer id, Usuario datosActualizados) {
        Optional<Usuario> usuarioBuscado = this.usuarioRepositorio.findById(id);
        if ((!usuarioBuscado.isPresent())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No se encontro el usuario buscado por el id:" + id + "suministrado"
            );
        }

        Usuario usuarioEncontrado = usuarioBuscado.get();


        usuarioEncontrado.setNombres(datosActualizados.getNombres());
        usuarioEncontrado.setCorreo(datosActualizados.getCorreo());

        Usuario usuarioActualizado = this.usuarioRepositorio.save(usuarioEncontrado);

        if (usuarioActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar el usuario");
        }

        return this.iUsuarioMapa.convertir_usuario_a_usuariogenericodto(usuarioActualizado);
    };
}
