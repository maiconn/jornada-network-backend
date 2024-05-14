package com.jornada.socialnetwork.mapper;

import com.jornada.socialnetwork.dto.request.DadosPrincipaisNovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.request.UsuarioContatoDTO;
import com.jornada.socialnetwork.dto.response.HabilidadeUsuarioResponseDTO;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.entity.UsuarioContatoEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import com.jornada.socialnetwork.entity.UsuarioHabilidadeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Base64;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "fotoPerfil", target = "fotoPerfil", ignore = true)
    UsuarioResponseDTO toDto(UsuarioEntity entity);

    @Mapping(source = "usuarioHabilidadePK.idHabilidade", target = "idHabilidade")
    @Mapping(source = "habilidade.descricao", target = "descricao")
    HabilidadeUsuarioResponseDTO converterHabilidadeEntityParaDTO(UsuarioHabilidadeEntity habilidade);

    @Mapping(source = "contato.descricao", target = "descricaoContato")
    @Mapping(source = "contato.idContato", target = "idContato")
    UsuarioContatoDTO converterContatoEntityParaDTO(UsuarioContatoEntity contato);


    default UsuarioResponseDTO toDtoCompleto(UsuarioEntity entity) {
        UsuarioResponseDTO responseDTO = toDto(entity);
        if(entity.getFotoPerfil() != null) {
            responseDTO.setFotoPerfil(Base64.getEncoder().encodeToString(entity.getFotoPerfil()));
        }
        responseDTO.setHabilidades(entity.getHabilidades().stream()
                .map(this::converterHabilidadeEntityParaDTO)
                .collect(Collectors.toSet()));
        responseDTO.setContatos(entity.getContatos().stream()
                .map(this::converterContatoEntityParaDTO)
                .collect(Collectors.toSet()));
        return responseDTO;
    }

    UsuarioEntity toEntity(DadosPrincipaisNovoUsuarioRequestDTO dadosPrincipaisNovoUsuarioRequestDTO);
}
