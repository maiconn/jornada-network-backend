package com.jornada.socialnetwork.security;


import com.jornada.socialnetwork.entity.PermissaoEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import com.jornada.socialnetwork.repository.PermissaoRepository;
import com.jornada.socialnetwork.service.UsuarioAutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UsuarioAutenticacaoService usuarioAutenticacaoService;
    private final PermissaoRepository permissaoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioAutenticacaoService.findByUsuario(username);

        if(usuarioEntityOptional.isPresent()){
            UsuarioEntity usuarioEntity = usuarioEntityOptional.get();
            usuarioEntity.getPermissoes().forEach(verificarExistenciaNome());
            return usuarioEntity;
        }

        throw new UsernameNotFoundException("User not found");
    }

    private Consumer<PermissaoEntity> verificarExistenciaNome() {
        return permissaoEntity -> {
            if (permissaoEntity.getNome() == null) {
                permissaoEntity.setNome(permissaoRepository.findById(permissaoEntity.getIdPermissao()).get().getNome());
            }
        };
    }
}
