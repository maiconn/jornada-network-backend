package com.jornada.socialnetwork.mapper;

import com.jornada.socialnetwork.dto.response.HabilidadeResponseDTO;
import com.jornada.socialnetwork.dto.response.HabilidadeUsuarioResponseDTO;
import com.jornada.socialnetwork.entity.HabilidadeEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import com.jornada.socialnetwork.entity.UsuarioHabilidadeEntity;
import com.jornada.socialnetwork.entity.pk.UsuarioHabilidadePK;
import org.mapstruct.Mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface HabilidadeMapper {

    HabilidadeResponseDTO toDto(HabilidadeEntity entity);
    HabilidadeEntity toEntity(HabilidadeUsuarioResponseDTO dto);

    default Set<UsuarioHabilidadeEntity> toEntityList(List<HabilidadeUsuarioResponseDTO> habilidadeUsuarioResponseDTOS, UsuarioEntity usuario) {
        if(habilidadeUsuarioResponseDTOS == null) {
            return new HashSet<>();
        }
        return habilidadeUsuarioResponseDTOS.stream()
                .map((habilidadeUsuarioResponseDTO -> {
                    UsuarioHabilidadeEntity entity = new UsuarioHabilidadeEntity();
                    entity.setNivel(0); // TODO por enquanto ficará zerado, no futuro o usuário poderá dizer qual o lvl de habilidade
                    entity.setHabilidade(this.toEntity(habilidadeUsuarioResponseDTO));
                    entity.setUsuario(usuario);
                    entity.setUsuarioHabilidadePK(new UsuarioHabilidadePK(usuario.getIdUsuario(), habilidadeUsuarioResponseDTO.getIdHabilidade()));
                    return entity;
                }))
                .collect(Collectors.toSet());
    }

}
