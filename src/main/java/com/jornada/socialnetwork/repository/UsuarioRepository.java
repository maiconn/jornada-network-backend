package com.jornada.socialnetwork.repository;

import com.jornada.socialnetwork.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    @EntityGraph(attributePaths = {"permissoes"})
    Optional<UsuarioEntity> findByUsuario(String usuario);
    Boolean existsByUsuario(String usuario);
    Boolean existsByEmail(String email);
}
