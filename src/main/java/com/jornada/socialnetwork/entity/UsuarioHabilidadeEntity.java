package com.jornada.socialnetwork.entity;

import com.jornada.socialnetwork.entity.pk.UsuarioHabilidadePK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "usuario_habilidade")
public class UsuarioHabilidadeEntity {
    @EmbeddedId
    private UsuarioHabilidadePK usuarioHabilidadePK;
    private Integer nivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habilidade", referencedColumnName = "id_habilidade", insertable = false, updatable = false)
    private HabilidadeEntity habilidade;
}
