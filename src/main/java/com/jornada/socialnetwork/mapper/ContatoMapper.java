package com.jornada.socialnetwork.mapper;

import com.jornada.socialnetwork.dto.request.UsuarioContatoDTO;
import com.jornada.socialnetwork.dto.response.ContatoResponseDTO;
import com.jornada.socialnetwork.entity.ContatoEntity;
import com.jornada.socialnetwork.entity.UsuarioContatoEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import com.jornada.socialnetwork.entity.UsuarioHabilidadeEntity;
import com.jornada.socialnetwork.entity.pk.UsuarioContatoPK;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContatoMapper {

    ContatoResponseDTO toDto(ContatoEntity entity);
    ContatoEntity toEntity(UsuarioContatoDTO dto);
    UsuarioContatoEntity toUsuarioContatoEntity(UsuarioContatoDTO dto);
    default Set<UsuarioContatoEntity>  toUsuarioContatoEntity(Set<UsuarioContatoDTO> usuarioContatoDTOS, UsuarioEntity usuario) {
        if(usuarioContatoDTOS == null) {
            return null;
        }
        return usuarioContatoDTOS.stream()
                .map(usuarioContatoDTO -> {
                    UsuarioContatoEntity usuarioContatoEntity = this.toUsuarioContatoEntity(usuarioContatoDTO);
                    usuarioContatoEntity.setContato(this.toEntity(usuarioContatoDTO));
                    usuarioContatoEntity.setUsuario(usuario);
                    usuarioContatoEntity.setPk(new UsuarioContatoPK(usuario.getIdUsuario(), usuarioContatoDTO.getIdContato()));
                    return usuarioContatoEntity;
                })
                .collect(Collectors.toSet());
    };

}
