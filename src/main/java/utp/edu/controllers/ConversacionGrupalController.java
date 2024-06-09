package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.entities.ConversacionGrupal;
import utp.edu.models.entities.MensajeConversacionGrupal;
import utp.edu.services.IConversacionGrupalService;

import java.util.List;

@RestController
@RequestMapping("/api/conversacion")
public class ConversacionGrupalController {

    @Autowired
    private IConversacionGrupalService conversacionGrupalService;

    @PostMapping("/iniciar/{grupoId}")
    public ResponseEntity<ConversacionGrupal> iniciarConversacion(@PathVariable Long grupoId) {
        return ResponseEntity.ok(conversacionGrupalService.iniciarConversacion(grupoId));
    }

    @GetMapping("/mensajes/{conversacionId}")
    public ResponseEntity<List<MensajeConversacionGrupal>> obtenerMensajes(@PathVariable Long conversacionId) {
        return ResponseEntity.ok(conversacionGrupalService.obtenerMensajes(conversacionId));
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<ConversacionGrupal> obtenerConversacionPorGrupo(@PathVariable Long grupoId) {
        ConversacionGrupal conversacion = conversacionGrupalService.obtenerConversacionPorGrupo(grupoId);
        if (conversacion != null) {
            return ResponseEntity.ok(conversacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
