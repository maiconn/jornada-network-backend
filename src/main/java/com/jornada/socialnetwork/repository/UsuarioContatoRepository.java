package com.jornada.socialnetwork.repository;

import com.jornada.socialnetwork.entity.UsuarioContatoEntity;
import com.jornada.socialnetwork.entity.pk.UsuarioContatoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioContatoRepository extends JpaRepository<UsuarioContatoEntity, UsuarioContatoPK> {

}
