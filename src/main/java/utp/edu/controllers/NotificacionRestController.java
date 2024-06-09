package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.entities.Notificacion;
import utp.edu.services.INotificacionService;

import java.util.List;

@RestController
@RequestMapping("/notifications")

public class NotificacionRestController {
    @Autowired
    private INotificacionService service;

    @PostMapping("/api/grupos/{grupoId}/alertas")
    public ResponseEntity<?> crearAlerta(@PathVariable Long grupoId, @RequestBody String mensaje) {
        service.crearNotification(grupoId, mensaje);
        return ResponseEntity.ok().build();
    }

    //Listar notificaciones del grupo
    @GetMapping("/group/{groupId}")
    public List<Notificacion> getNotificationsByGroup(@PathVariable Long groupId) {
        return service.getNotificationsByGroup(groupId);
    }

}
