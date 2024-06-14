package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.HobbyDao;
import utp.edu.models.dao.PerfilDao;
import utp.edu.models.dao.PerfilHobbyDao;
import utp.edu.models.dao.PersonaDao;
import utp.edu.models.dto.CrearHobbyDTO;
import utp.edu.models.dto.EliminarHobbyDTO;
import utp.edu.models.entities.Hobby;
import utp.edu.models.entities.Perfil;
import utp.edu.models.entities.PerfilHobby;
import utp.edu.models.entities.Persona;

import java.util.List;
import java.util.Optional;

@Service
public class HobbyServiceImpl implements IHobbyService {

    @Autowired
    private HobbyDao hobbyDao;

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private PerfilDao perfilDao;

    @Autowired
    private PerfilHobbyDao perfilHobbyDao;

    @Override
    public List<Hobby> listHobbiesByCod(String codigoPersona) {
        return hobbyDao.listHobbiesByCod(codigoPersona);
    }

    @Override
    public PerfilHobby crearHobby(CrearHobbyDTO crearHobbyDTO) {
        Optional<Persona> personaEncontrada = personaDao.findPersonaByCod(crearHobbyDTO.getCodigoPersona());
        if (personaEncontrada.isPresent()) {
            Optional<Perfil> perfilEncontrado = perfilDao.findPerfilByPersona(personaEncontrada.get().getId());

            if (perfilEncontrado.isPresent()) {
                // Guardar hobby
                Hobby nuevoHobby = new Hobby();
                nuevoHobby.setNombre(crearHobbyDTO.getNom_hobby());
                hobbyDao.save(nuevoHobby);

                // Guardar relación
                PerfilHobby perfilHobby = new PerfilHobby();
                perfilHobby.setPerfil(perfilEncontrado.get());
                perfilHobby.setHobby(nuevoHobby);

                return perfilHobbyDao.save(perfilHobby);
            } else {
                throw new RuntimeException("Perfil no encontrado para la persona con código " + crearHobbyDTO.getCodigoPersona());
            }
        }
        throw new RuntimeException("No se pudo agregar el hobby porque el usuario de código " + crearHobbyDTO.getCodigoPersona() + " no se encontró");
    }

    @Override
    public void deletePerfilHobby(EliminarHobbyDTO eliminarHobbyDTO) {
        String codigoPersona = eliminarHobbyDTO.getCodigoPersona();
        Long idHobby = eliminarHobbyDTO.getIdHobby();
        Optional<Persona> personaEncontrada = personaDao.findPersonaByCod(codigoPersona);
        if (personaEncontrada.isPresent()) {
            Optional<Perfil> perfilEncontrado = perfilDao.findPerfilByPersona(personaEncontrada.get().getId());

            if (perfilEncontrado.isPresent()) {
                // Eliminar hobby
                PerfilHobby perfilHobby = hobbyDao.findPerfilHobby(codigoPersona, idHobby).orElseThrow(() ->
                        new RuntimeException("Hobby no encontrado para el usuario con código " + codigoPersona));

                perfilHobbyDao.deleteById(perfilHobby.getId());
            } else {
                throw new RuntimeException("Perfil no encontrado para la persona con código " + codigoPersona);
            }
        } else {
            throw new RuntimeException("No se pudo eliminar el hobby porque el usuario de código " + codigoPersona + " no se encontró");
        }
    }
}
