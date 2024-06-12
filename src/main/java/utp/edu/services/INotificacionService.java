package utp.edu.services;

import utp.edu.models.entities.Notificacion;
import java.util.List;
import java.util.Optional;

public interface INotificacionService {
    Notificacion crearNotification(Long grupoId, Notificacion notificacion);
    List<Notificacion> getNotificationsByGroup(Long groupId);
    void pinNotification(Long notificationId, boolean isPinned);
    Optional<Notificacion> getPinnedNotificationByGroup(Long groupId);
    Optional<Notificacion> getNotificationById(Long notificationId); // Nuevo método
}
