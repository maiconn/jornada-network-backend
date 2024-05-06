package com.jornada.socialnetwork.security;


import com.jornada.socialnetwork.service.UsuarioAutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final UsuarioAutenticacaoService usuarioAutenticacaoService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // verificar token
        String tokenComBearer = request.getHeader("Authorization");

        // validar
        UsernamePasswordAuthenticationToken usuario = usuarioAutenticacaoService.verificarToken(tokenComBearer);

        // atribuir objeto do spring
        SecurityContextHolder.getContext().setAuthentication(usuario);

        filterChain.doFilter(request, response);
    }
}
