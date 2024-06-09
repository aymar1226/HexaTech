package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.entities.MensajeConversacionGrupal;
import utp.edu.services.IConversacionGrupalService;

@Controller
public class MensajeController {

    @Autowired
    private IConversacionGrupalService conversacionGrupalService;

    @MessageMapping("/enviarMensaje")
    @SendTo("/topic/mensajes")
    public MensajeConversacionGrupal enviarMensajeWebSocket(MensajeRequest mensajeRequest) {
        return conversacionGrupalService.enviarMensaje(
                mensajeRequest.getConversacionId(),
                mensajeRequest.getCodigoPersona(),
                mensajeRequest.getMensaje()
        );
    }

    @PostMapping("/api/conversacion/enviar")
    public ResponseEntity<MensajeConversacionGrupal> enviarMensajeRest(@RequestBody MensajeRequest mensajeRequest) {
        return ResponseEntity.ok(conversacionGrupalService.enviarMensaje(
                mensajeRequest.getConversacionId(),
                mensajeRequest.getCodigoPersona(),
                mensajeRequest.getMensaje()
        ));
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
