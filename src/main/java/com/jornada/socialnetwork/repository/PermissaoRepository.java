package com.jornada.socialnetwork.repository;

import com.jornada.socialnetwork.entity.PermissaoEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissaoRepository extends JpaRepository<PermissaoEntity, Long> {

}
