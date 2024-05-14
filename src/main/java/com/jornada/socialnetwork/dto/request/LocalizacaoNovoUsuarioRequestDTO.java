package com.jornada.socialnetwork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalizacaoNovoUsuarioRequestDTO {
    private Integer idCidade;
    private Integer idEstado;
}
