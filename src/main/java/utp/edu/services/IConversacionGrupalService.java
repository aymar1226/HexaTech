package utp.edu.services;

import utp.edu.models.entities.ConversacionGrupal;
import utp.edu.models.entities.MensajeConversacionGrupal;

import java.util.List;

public interface IConversacionGrupalService {
    ConversacionGrupal iniciarConversacion(Long id_grupo);
    List<MensajeConversacionGrupal> obtenerMensajes(Long id_conversacion_grupal);
    MensajeConversacionGrupal enviarMensaje(Long id_conversacion_grupal, String codigoPersona, String mensaje);
    ConversacionGrupal obtenerConversacionPorGrupo(Long grupoId);  // Nuevo método
}
