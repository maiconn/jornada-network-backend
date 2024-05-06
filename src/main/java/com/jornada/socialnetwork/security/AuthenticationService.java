package com.jornada.socialnetwork.security;


import com.jornada.socialnetwork.entity.UsuarioEntity;
import com.jornada.socialnetwork.service.UsuarioAutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UsuarioAutenticacaoService usuarioAutenticacaoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioAutenticacaoService.findByUsuario(username);

        if(usuarioEntityOptional.isPresent()){
            return usuarioEntityOptional.get();
        }

        throw new UsernameNotFoundException("User not found");
    }
}
