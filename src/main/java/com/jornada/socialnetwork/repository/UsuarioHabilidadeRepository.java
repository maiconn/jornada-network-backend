package com.jornada.socialnetwork.repository;

import com.jornada.socialnetwork.entity.UsuarioContatoEntity;
import com.jornada.socialnetwork.entity.UsuarioHabilidadeEntity;
import com.jornada.socialnetwork.entity.pk.UsuarioContatoPK;
import com.jornada.socialnetwork.entity.pk.UsuarioHabilidadePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioHabilidadeRepository extends JpaRepository<UsuarioHabilidadeEntity, UsuarioHabilidadePK> {

}
