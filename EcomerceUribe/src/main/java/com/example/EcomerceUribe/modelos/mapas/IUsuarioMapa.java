package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioDTO.UsuarioEspecialDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioDTO.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUsuarioMapa {

    //Se construyen 2 funciones por mapa

    //1. que transforme 1 modelo en 1 DTO

    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "documento", target = "documento")
    UsuarioGenericoDTO usuarioGenericoToDTO(Usuario usuario);

    //2. que transforme una list<modelo> en una list<dto>
    List<UsuarioGenericoDTO> listaUsuarioGenericoToDTO(List<Usuario> lista);


    //3.
    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "documento", target = "documento")
    @Mapping(source = "contraseña", target = "contraseña")
    UsuarioEspecialDTO usuarioEspecialToDTO(Usuario usuario);


    //4. que transforme una list<modelo> en una list<dto>
    List<UsuarioEspecialDTO> listaUsuarioEspecialToDTO(List<Usuario> lista);
}
