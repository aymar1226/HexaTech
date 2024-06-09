package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.ConversacionGrupalDao;
import utp.edu.models.dao.GrupoDao;
import utp.edu.models.dao.MensajeConversacionGrupalDao;
import utp.edu.models.dao.PersonaDao;
import utp.edu.models.entities.ConversacionGrupal;
import utp.edu.models.entities.Grupo;
import utp.edu.models.entities.MensajeConversacionGrupal;
import utp.edu.models.entities.Persona;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConversacionGrupalServiceImpl implements IConversacionGrupalService {

    @Autowired
    private ConversacionGrupalDao conversacionGrupalDao;

    @Autowired
    private MensajeConversacionGrupalDao mensajeConversacionGrupalDao;

    @Autowired
    private GrupoDao grupoDao;

    @Autowired
    private PersonaDao personaDao;

    @Override
    public ConversacionGrupal iniciarConversacion(Long grupoId) {
        Optional<ConversacionGrupal> conversacionExistente = conversacionGrupalDao.findByGrupoId(grupoId);
        if (conversacionExistente.isPresent()) {
            return conversacionExistente.get();
        }

        Optional<Grupo> grupoOpt = grupoDao.findById(grupoId);
        if (grupoOpt.isPresent()) {
            ConversacionGrupal conversacion = new ConversacionGrupal();
            conversacion.setGrupo(grupoOpt.get());
            conversacion.setFechaInicio(new Date());
            return conversacionGrupalDao.save(conversacion);
        } else {
            throw new IllegalArgumentException("Grupo no encontrado");
        }
    }

    @Override
    public List<MensajeConversacionGrupal> obtenerMensajes(Long id_conversacion_grupal) {
        return mensajeConversacionGrupalDao.findByConversacionGrupalId(id_conversacion_grupal);
    }

    @Override
    public MensajeConversacionGrupal enviarMensaje(Long id_conversacion_grupal, String codigoPersona, String mensaje) {
        Optional<ConversacionGrupal> conversacionOpt = conversacionGrupalDao.findById(id_conversacion_grupal);
        Optional<Persona> personaOpt = personaDao.findPersonaByCod(codigoPersona);

        if (conversacionOpt.isPresent() && personaOpt.isPresent()) {
            MensajeConversacionGrupal nuevoMensaje = new MensajeConversacionGrupal();
            nuevoMensaje.setConversacionGrupal(conversacionOpt.get());
            nuevoMensaje.setPersona(personaOpt.get());
            nuevoMensaje.setMensaje(mensaje);
            nuevoMensaje.setFechaEnvio(new Date());
            return mensajeConversacionGrupalDao.save(nuevoMensaje);
        } else {
            throw new IllegalArgumentException("Conversacion grupal o persona no encontrada");
        }
    }

    @Override
    public ConversacionGrupal obtenerConversacionPorGrupo(Long grupoId) {
        return conversacionGrupalDao.findByGrupoId(grupoId).orElse(null);
    }
}
