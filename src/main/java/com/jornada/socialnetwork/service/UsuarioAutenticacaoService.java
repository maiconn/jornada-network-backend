package com.jornada.socialnetwork.service;


import com.jornada.socialnetwork.dto.AutenticacaoDTO;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import com.jornada.socialnetwork.exceptions.BusinessException;
import com.jornada.socialnetwork.mapper.UsuarioMapper;
import com.jornada.socialnetwork.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioAutenticacaoService {
    private static final String BEARER = "Bearer ";
    private static final String PERMISSOES = "PERMISSOES";
    private static final String API_NAME = "jornada-api";
    private final UsuarioRepository repository;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper mapper;

    public UsuarioAutenticacaoService(@Lazy UsuarioRepository usuarioRepository,
                                      @Lazy AuthenticationManager authenticationManager,
                                      @Lazy UsuarioMapper usuarioMapper) {
        this.authenticationManager = authenticationManager;
        this.repository = usuarioRepository;
        this.mapper = usuarioMapper;
    }


    @Value("${jwt.validade.token}")
    private String validadeToken;

    @Value("${jwt.secret}")
    private String secret;

    public UsuarioResponseDTO autenticar(AutenticacaoDTO autenticacaoDTO) throws BusinessException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        autenticacaoDTO.getUsuario(),
                        autenticacaoDTO.getSenha()
                );
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            usernamePasswordAuthenticationToken);

            UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();

            LocalDate expiration = LocalDate.now().plusDays(Integer.parseInt(validadeToken));
            Date exp = Date.from(expiration.atStartOfDay(ZoneId.systemDefault()).toInstant());

            List<String> regras = usuario.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            String token = BEARER +
                    Jwts.builder()
                            .setIssuer(API_NAME)
                            .claim(PERMISSOES, regras)
                            .setSubject(usuario.getIdUsuario().toString())
                            .setIssuedAt(new Date())
                            .setExpiration(exp)
                            .signWith(SignatureAlgorithm.HS256, secret)
                            .compact();
            UsuarioResponseDTO dto = mapper.toDto(usuario);
            if (usuario.getFotoPerfil() != null) {
                dto.setFotoPerfil(Base64.getEncoder().encodeToString(usuario.getFotoPerfil()));
            }
            dto.setToken(token);
            return dto;
        } catch (AuthenticationException ex) {
            throw new BusinessException("Usuario ou senha inválidos");
        }
    }

    public UsernamePasswordAuthenticationToken verificarToken(String token) {
        if (token == null) {
            return null;    
        }

        String tokenLimpo = token.replace(BEARER, "");

        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(tokenLimpo)
                .getBody();
        String user = body.getSubject();
        if(user != null) {
            List<String> cargos = body.get(PERMISSOES, List.class);
            List<SimpleGrantedAuthority> grantedAuthorities = cargos.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
        }
        return null;
    }

    public Long getIdLoggedUser() {
        String findUserId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.valueOf(findUserId);
    }

    public UsuarioResponseDTO retornarUsuarioLogado() throws BusinessException {
        UsuarioEntity entity = retornarUsuarioLogadoEntity();
        return mapper.toDto(entity);
    }

    public UsuarioEntity retornarUsuarioLogadoEntity() throws BusinessException {
        return repository.findById(getIdLoggedUser())
                .orElseThrow(() ->
                        new BusinessException("Usuário não encontrado!"));
    }

    public Optional<UsuarioEntity> findByUsuario(String usuario) {
        return repository.findByUsuario(usuario);
    }
}
