package com.jornada.socialnetwork.entity.pk;

import com.jornada.socialnetwork.entity.ContatoEntity;
import com.jornada.socialnetwork.entity.HabilidadeEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioHabilidadePK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_habilidade")
    private Long idHabilidade;

}
