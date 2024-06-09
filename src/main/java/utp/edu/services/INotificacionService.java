package utp.edu.services;


import utp.edu.models.entities.Notificacion;

import javax.management.Notification;
import java.util.List;

public interface INotificacionService {

    public Notificacion crearNotification(Long grupoId, String mensaje);
    public List<Notificacion> getNotificationsByGroup(Long groupId);

    }
