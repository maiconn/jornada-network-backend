package com.jornada.socialnetwork.mapper;

import com.jornada.socialnetwork.dto.request.NovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.request.UsuarioRequestDTO;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "fotoPerfil", target = "fotoPerfil", ignore = true)
    UsuarioResponseDTO toDto(UsuarioEntity entity);

    UsuarioEntity toEntity(NovoUsuarioRequestDTO novoUsuarioRequestDTO);
}
