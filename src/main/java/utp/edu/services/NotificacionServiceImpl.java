package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.GrupoDao;
import utp.edu.models.dao.NotificacionDao;
import utp.edu.models.entities.Grupo;
import utp.edu.models.entities.Notificacion;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionServiceImpl implements INotificacionService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificacionServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Autowired
    private NotificacionDao notificacionDao;
    @Autowired
    private GrupoDao grupoDao;

    @Override
    public Notificacion crearNotification(Long grupoId, Notificacion notificacion) {
        Grupo grupo = grupoDao.findById(grupoId).get();
        notificacion.setGrupo(grupo);
        notificacionDao.save(notificacion);
        return notificacion;
    }

    @Override
    public List<Notificacion> getNotificationsByGroup(Long groupId) {
        return notificacionDao.findByGroupId(groupId);
    }

    @Override
    public void pinNotification(Long notificationId, boolean isPinned) {
        Notificacion notificacion = notificacionDao.findById(notificationId).get();
        notificacion.setPinned(isPinned);
        notificacionDao.save(notificacion);
    }

    @Override
    public Optional<Notificacion> getPinnedNotificationByGroup(Long groupId) {
        return notificacionDao.findPinnedNotificationByGroupId(groupId);
    }
}
