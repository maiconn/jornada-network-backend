package com.jornada.socialnetwork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioContatoDTO {
    private Long idContato;
    private String descricaoContato;
    private String descricao;
    private Boolean visivel;
}
