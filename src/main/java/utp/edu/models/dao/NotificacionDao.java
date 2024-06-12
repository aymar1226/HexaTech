package utp.edu.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utp.edu.models.entities.Notificacion;
import java.util.List;
import java.util.Optional;

public interface NotificacionDao extends JpaRepository<Notificacion, Long> {

    @Query("SELECT n FROM Notificacion n WHERE n.grupo.id = :idGrupo")
    List<Notificacion> findByGroupId(@Param("idGrupo") Long idGrupo);

    @Query("SELECT n FROM Notificacion n WHERE n.grupo.id = :idGrupo AND n.isPinned = true")
    Optional<Notificacion> findPinnedNotificationByGroupId(@Param("idGrupo") Long idGrupo);
}
