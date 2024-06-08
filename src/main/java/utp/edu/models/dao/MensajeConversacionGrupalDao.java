package utp.edu.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.models.entities.MensajeConversacionGrupal;

import java.util.List;

public interface MensajeConversacionGrupalDao  extends JpaRepository<MensajeConversacionGrupal, Long> {
    List<MensajeConversacionGrupal> findByConversacionGrupalId(Long id_conversacion_grupal);
}
