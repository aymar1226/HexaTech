package utp.edu.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.models.entities.ConversacionGrupal;

import java.util.Optional;

public interface ConversacionGrupalDao extends JpaRepository<ConversacionGrupal, Long> {
    Optional<ConversacionGrupal> findByGrupoId(Long grupoId);
}
