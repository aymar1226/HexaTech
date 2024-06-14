package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.PerfilDao;
import utp.edu.models.dao.PersonaDao;
import utp.edu.models.dto.ActualizarInfoAdicionalDTO;
import utp.edu.models.dto.InfoDTO;
import utp.edu.models.entities.Perfil;
import utp.edu.models.entities.Persona;

import java.util.Optional;

@Service
public class PerfilServiceImpl implements IPerfilService {

    @Autowired
    private PerfilDao perfilDao;

    @Autowired
    private PersonaDao personaDao;

    @Override
    public InfoDTO getInfo(String codigoPersona) {
        Optional<Persona> persona = personaDao.findPersonaByCod(codigoPersona);
        if (persona.isPresent()) {
            Optional<Perfil> perfil = perfilDao.findPerfilByPersona(persona.get().getId());
            if (perfil.isPresent()) {
                Perfil p = perfil.get();
                return new InfoDTO(
                        p.getPersona().getNombres(),
                        p.getPersona().getAp_paterno(),
                        p.getPersona().getAp_materno(),
                        p.getInfoAdicional(),  // Ensure the field name matches your entity
                        p.getDescripcion()
                );
            } else {
                throw new RuntimeException("Perfil no encontrado para la persona con código " + codigoPersona);
            }
        } else {
            throw new RuntimeException("Persona no encontrada con código " + codigoPersona);
        }
    }

    @Override
    public void actualizarInfoAdicional(ActualizarInfoAdicionalDTO actualizarInfoAdicionalDTO) {
        Optional<Persona> persona = personaDao.findPersonaByCod(actualizarInfoAdicionalDTO.getCodigoPersona());
        if (persona.isPresent()) {
            Optional<Perfil> perfil = perfilDao.findPerfilByPersona(persona.get().getId());
            if (perfil.isPresent()) {
                Perfil p = perfil.get();
                p.setInfoAdicional(actualizarInfoAdicionalDTO.getInfoAdicional());  // Ensure the field name matches your entity
                perfilDao.save(p);
            } else {
                throw new RuntimeException("Perfil no encontrado para la persona con código " + actualizarInfoAdicionalDTO.getCodigoPersona());
            }
        } else {
            throw new RuntimeException("Persona no encontrada con código " + actualizarInfoAdicionalDTO.getCodigoPersona());
        }
    }
}
