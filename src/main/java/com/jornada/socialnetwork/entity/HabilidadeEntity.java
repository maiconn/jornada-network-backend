package com.jornada.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity(name = "habilidade")
public class HabilidadeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_habilidade")
    private Long idHabilidade;
    private String descricao;
    private Boolean ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "habilidade")
    private Set<UsuarioHabilidadeEntity> usuarios;
}
