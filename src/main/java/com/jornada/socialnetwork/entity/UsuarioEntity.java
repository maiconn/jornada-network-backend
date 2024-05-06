package com.jornada.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity(name = "usuario")
public class UsuarioEntity implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;
    private String nome;
    private String email;
    @Column(name = "qtd_seguindo")
    private Long qtdSeguindo;
    @Column(name = "qtd_seguidores")
    private Long qtdSeguidores;
    @Column(name = "qtd_postagens")
    private Long qtdPostagens;
    private String bio;
    @Column(name = "id_cidade")
    private Integer idCidade;
    @Column(name = "id_estado")
    private Integer idEstado;
    @Column(name = "foto_perfil")
    private byte[] fotoPerfil;
    private Boolean ativo;
    private String senha;
    private String usuario;

    @ManyToMany
    @JoinTable(name = "usuario_permissao",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_permissao", referencedColumnName = "id_permissao"))
    private Set<PermissaoEntity> permissoes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissoes;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }
}
