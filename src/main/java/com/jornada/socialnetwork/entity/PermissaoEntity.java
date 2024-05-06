package com.jornada.socialnetwork.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@Entity(name = "permissao")
public class PermissaoEntity implements GrantedAuthority {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_permissao")
    private Long idPermissao;
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissoes")
    private Set<UsuarioEntity> usuarios;

    @Override
    public String getAuthority() {
        return nome;
    }
}
