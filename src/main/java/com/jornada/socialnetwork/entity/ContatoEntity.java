package com.jornada.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity(name = "contato")
public class ContatoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_contato")
    private Long idContato;

    private String descricao;

    private Boolean ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contato")
    private Set<UsuarioContatoEntity> usuarios;
}
