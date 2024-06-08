package utp.edu.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.models.entities.Notificacion;

public interface NotificacionDao extends JpaRepository<Notificacion, Long> {
}
