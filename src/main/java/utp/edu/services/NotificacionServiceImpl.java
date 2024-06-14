package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.GrupoDao;
import utp.edu.models.dao.NotificacionDao;
import utp.edu.models.dao.PersonaDao;
import utp.edu.models.dto.PersonaDTO;
import utp.edu.models.entities.Grupo;
import utp.edu.models.entities.Notificacion;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import utp.edu.models.entities.Persona;

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

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Notificacion crearNotification(Long grupoId, Notificacion notificacion) {
        Grupo grupo = grupoDao.findById(grupoId).get();
        notificacion.setGrupo(grupo);
        notificacionDao.save(notificacion);

//        Optional<Persona> lider = personaDao.findCodigoLiderByGroup(grupoId);
//        String codigoLider = lider.get().getCodigo();

        List<PersonaDTO> listaGrupo=personaDao.getPersonasByGrupo(notificacion.getGrupo().getId());

        for (PersonaDTO miembro : listaGrupo){
            SimpleMailMessage message = new SimpleMailMessage();
 //         message.setFrom(codigoLider+"@utp.edu.pe");
            message.setTo(miembro.getCodigo()+"@utp.edu.pe");
            message.setSubject("Nueva alerta de tu grupo '"+notificacion.getGrupo().getNombre()+"' del curso '"+notificacion.getGrupo().getCurso().getNombre()+"'");
            message.setText(notificacion.getMensaje());
            mailSender.send(message);
        }
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

    @Override
    public Optional<Notificacion> getNotificationById(Long notificationId) {
        return notificacionDao.findById(notificationId);
    }
}
