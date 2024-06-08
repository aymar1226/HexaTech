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

    @PostMapping("/enviar")
    public ResponseEntity<MensajeConversacionGrupal> enviarMensaje(@RequestBody MensajeRequest mensajeRequest) {
        return ResponseEntity.ok(conversacionGrupalService.enviarMensaje(mensajeRequest.getConversacionId(), mensajeRequest.getCodigoPersona(), mensajeRequest.getMensaje()));
    }

    public static class MensajeRequest {
        private Long conversacionId;
        private String codigoPersona;
        private String mensaje;

        // Getters y Setters

        public Long getConversacionId() {
            return conversacionId;
        }

        public void setConversacionId(Long conversacionId) {
            this.conversacionId = conversacionId;
        }

        public String getCodigoPersona() {
            return codigoPersona;
        }

        public void setCodigoPersona(String codigoPersona) {
            this.codigoPersona = codigoPersona;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}
