package com.jornada.socialnetwork.mapper;

import com.jornada.socialnetwork.dto.request.DadosPrincipaisNovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.response.ContatoResponseDTO;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.entity.ContatoEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContatoMapper {

    ContatoResponseDTO toDto(ContatoEntity entity);

}
