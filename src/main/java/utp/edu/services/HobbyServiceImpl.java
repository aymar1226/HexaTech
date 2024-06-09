package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.HobbyDao;
import utp.edu.models.dao.PerfilDao;
import utp.edu.models.dao.PerfilHobbyDao;
import utp.edu.models.dao.PersonaDao;
import utp.edu.models.dto.CrearHobbyDTO;
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

            // Guardar hobby
            Hobby nuevoHobby = new Hobby();
            nuevoHobby.setNombre(crearHobbyDTO.getNom_hobby());
            hobbyDao.save(nuevoHobby);

            // Guardar relación
            PerfilHobby perfilHobby = new PerfilHobby();
            perfilHobby.setPerfil(perfilEncontrado.get());
            perfilHobby.setHobby(nuevoHobby);

            return perfilHobbyDao.save(perfilHobby);
        }
        throw new RuntimeException("No se pudo agregar el hobby porque el usuario de código " + crearHobbyDTO.getCodigoPersona() + " no se encontró");
    }

    @Override
    public void deletePerfilHobby(Long idPerfilHobby) {
        perfilHobbyDao.deleteById(idPerfilHobby);
    }
}
