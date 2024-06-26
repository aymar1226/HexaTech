package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.HabilidadDao;
import utp.edu.models.dao.PerfilDao;
import utp.edu.models.dao.PerfilHabilidadDao;
import utp.edu.models.dao.PersonaDao;
import utp.edu.models.dto.CrearHabilidadDTO;
import utp.edu.models.dto.EliminarHabilidadDTO;
import utp.edu.models.entities.Habilidad;
import utp.edu.models.entities.Perfil;
import utp.edu.models.entities.PerfilHabilidad;
import utp.edu.models.entities.Persona;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadServiceImpl implements IHabilidadService {

    @Autowired
    private HabilidadDao habilidadDao;

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private PerfilDao perfilDao;

    @Autowired
    private PerfilHabilidadDao perfilHabilidadDao;

    @Override
    public List<Habilidad> listAbilitiesByCod(String codigoPersona) {
        return habilidadDao.listAbilitiesByCod(codigoPersona);
    }

    @Override
    public PerfilHabilidad crearHabilidad(CrearHabilidadDTO crearHabilidadDTO) {
        Optional<Persona> personaEncontrada = personaDao.findPersonaByCod(crearHabilidadDTO.getCodigoPersona());
        if (personaEncontrada.isPresent()) {
            Optional<Perfil> perfilEncontrado = perfilDao.findPerfilByPersona(personaEncontrada.get().getId());

            if (perfilEncontrado.isPresent()) {
                // Guardar habilidad
                Habilidad nuevaHabilidad = new Habilidad();
                nuevaHabilidad.setNombre(crearHabilidadDTO.getNom_habilidad());
                habilidadDao.save(nuevaHabilidad);

                // Guardar relación
                PerfilHabilidad perfilHabilidad = new PerfilHabilidad();
                perfilHabilidad.setPerfil(perfilEncontrado.get());
                perfilHabilidad.setHabilidad(nuevaHabilidad);

                return perfilHabilidadDao.save(perfilHabilidad);
            } else {
                throw new RuntimeException("Perfil no encontrado para la persona con código " + crearHabilidadDTO.getCodigoPersona());
            }
        }
        throw new RuntimeException("No se pudo agregar la habilidad porque el usuario de código " + crearHabilidadDTO.getCodigoPersona() + " no se encontró");
    }

    @Override
    public void deletePerfilHabilidad(EliminarHabilidadDTO eliminarHabilidadDTO) {
        String codigoPersona = eliminarHabilidadDTO.getCodigoPersona();
        Long idHabilidad = eliminarHabilidadDTO.getIdHabilidad();
        Optional<Persona> personaEncontrada = personaDao.findPersonaByCod(codigoPersona);
        if (personaEncontrada.isPresent()) {
            Optional<Perfil> perfilEncontrado = perfilDao.findPerfilByPersona(personaEncontrada.get().getId());

            if (perfilEncontrado.isPresent()) {
                // Eliminar habilidad
                PerfilHabilidad perfilHabilidad = habilidadDao.findPerfilHabilidad(codigoPersona, idHabilidad).orElseThrow(() ->
                        new RuntimeException("Habilidad no encontrada para el usuario con código " + codigoPersona));

                perfilHabilidadDao.deleteById(perfilHabilidad.getId());
            } else {
                throw new RuntimeException("Perfil no encontrado para la persona con código " + codigoPersona);
            }
        } else {
            throw new RuntimeException("No se pudo eliminar la habilidad porque el usuario de código " + codigoPersona + " no se encontró");
        }
    }
}
