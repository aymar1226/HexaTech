package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.entities.Notificacion;
import utp.edu.services.INotificacionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificacionRestController {
    @Autowired
    private INotificacionService service;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/api/grupos/{grupoId}/alertas")
    public ResponseEntity<?> crearAlerta(@PathVariable Long grupoId, @RequestBody Notificacion notificacion) {
        Notificacion creada = service.crearNotification(grupoId, notificacion);
        messagingTemplate.convertAndSend("/topic/notifications/" + grupoId, creada);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/group/{groupId}")
    public List<Notificacion> getNotificationsByGroup(@PathVariable Long groupId) {
        return service.getNotificationsByGroup(groupId);
    }

    @PostMapping("/pin/{notificationId}")
    public ResponseEntity<?> pinNotification(@PathVariable Long notificationId, @RequestParam boolean isPinned) {
        service.pinNotification(notificationId, isPinned);

        Optional<Notificacion> notificacionOpt = service.getNotificationById(notificationId);
        if (notificacionOpt.isPresent()) {
            Notificacion notificacion = notificacionOpt.get();
            messagingTemplate.convertAndSend("/topic/notifications/" + notificacion.getGrupo().getId(), notificacion);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/group/{groupId}/pinned")
    public ResponseEntity<Notificacion> getPinnedNotificationByGroup(@PathVariable Long groupId) {
        Optional<Notificacion> notificacion = service.getPinnedNotificationByGroup(groupId);
        return notificacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
