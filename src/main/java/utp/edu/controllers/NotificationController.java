package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import utp.edu.models.dto.NotificacionDTO;
import utp.edu.models.entities.Notificacion;
import utp.edu.services.INotificacionService;

@Controller
public class NotificationController {
    @Autowired
    private INotificacionService service;

    @MessageMapping("/notificacion/{roomId}")
    @SendTo("/topic/{roomId}")
    public NotificacionDTO sendNotification(@DestinationVariable String roomId, NotificacionDTO notificacion) {
        // Aquí puedes agregar lógica adicional si es necesario
        return new NotificacionDTO(notificacion.getMensaje(), notificacion.getId_grupo(), notificacion.isPinned());
    }
}
