package com.jornada.socialnetwork.security;


import com.jornada.socialnetwork.service.UsuarioAutenticacaoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UsuarioAutenticacaoService usuarioAutenticacaoService;

    public SecurityConfiguration(@Lazy UsuarioAutenticacaoService usuarioAutenticacaoService) {
        this.usuarioAutenticacaoService = usuarioAutenticacaoService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(withDefaults());
        http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        http.authorizeHttpRequests((authz) -> authz

                .requestMatchers("/autenticacao/**").permitAll()
                .requestMatchers("/usuario/create").permitAll()

                .requestMatchers("/empresa/**").hasAnyRole("ADMIN", "EMPRESA")
                .requestMatchers("/personal/**").hasAnyRole("ADMIN", "EMPRESA")
                .requestMatchers("/aluno/**").hasAnyRole("ADMIN", "EMPRESA", "PERSONAL")

                .requestMatchers("/**").hasRole("ADMIN")

                .anyRequest().authenticated()
        );
        http.addFilterBefore(new TokenAuthenticationFilter(usuarioAutenticacaoService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
