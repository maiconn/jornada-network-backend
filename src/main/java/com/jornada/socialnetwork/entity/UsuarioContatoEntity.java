package com.jornada.socialnetwork.entity;

import com.jornada.socialnetwork.entity.pk.UsuarioContatoPK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "usuario_contato")
public class UsuarioContatoEntity {
    @EmbeddedId
    private UsuarioContatoPK pk;
    private String descricao;
    private Boolean publico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contato", referencedColumnName = "id_contato", insertable = false, updatable = false)
    private ContatoEntity contato;
}
