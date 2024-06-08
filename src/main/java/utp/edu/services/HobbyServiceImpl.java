package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.HobbyDao;
import utp.edu.models.dao.PerfilDao;
import utp.edu.models.dao.PerfilHobbyDao;
import utp.edu.models.dao.PersonaDao;
import utp.edu.models.dto.CrearHobbyDTO;
import utp.edu.models.dto.EliminarHobbyDTO;
import utp.edu.models.entities.*;

import java.util.List;
import java.util.Optional;

@Service
public class HobbyServiceImpl implements IHobbyService{
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
        if(personaEncontrada.isPresent()){
            Optional<Perfil> perfilEncontrado = perfilDao.findPerfilByPersona(personaEncontrada.get().getId());

            //GuardarHabilidad
            Hobby nuevoHobby = new Hobby();
            nuevoHobby.setNombre(crearHobbyDTO.getNom_hobby());
            hobbyDao.save(nuevoHobby);

            //GuardarRelacion
            PerfilHobby perfilHobby= new PerfilHobby();
            perfilHobby.setPerfil(perfilEncontrado.get());
            perfilHobby.setHobby(nuevoHobby);


            return perfilHobbyDao.save(perfilHobby);
        }
        throw new RuntimeException("No se pudo agregar el hobby porque el usuario de codigo "+ crearHobbyDTO.getCodigoPersona()+" no se encontro") ;

    }

    @Override
    public void deleteHobby(EliminarHobbyDTO hobbyDTO) {

        Optional<Persona> personaEncontrada = personaDao.findPersonaByCod(hobbyDTO.getCodigoPersona());

        if(personaEncontrada.isPresent()){
            hobbyDao.deleteHobby(hobbyDTO.getCodigoPersona(), hobbyDTO.getId_hobby());
            hobbyDao.deleteById(hobbyDTO.getId_hobby());

        }
        throw new RuntimeException("No se pudo eliminar el hobby porque el usuario de codigo "+ hobbyDTO.getCodigoPersona()+" no se encontro") ;

    }

}
