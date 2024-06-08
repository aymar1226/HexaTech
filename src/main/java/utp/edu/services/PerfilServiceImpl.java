package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.PerfilDao;
import utp.edu.models.dao.PersonaDao;
import utp.edu.models.dto.InfoDTO;
import utp.edu.models.dto.UpdateInfoDTO;
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
    public InfoDTO getInfoByCod(String codigoPersona) {

        return perfilDao.getInfoByCod(codigoPersona);
    }

    @Override
    public Perfil updateInfo(String codigo,InfoDTO infoDTO) {

        Optional<Persona> personaEncontrada = personaDao.findPersonaByCod(codigo);
        if(personaEncontrada.isPresent()) {
            Optional<Perfil> perfilEncontrado = perfilDao.findPerfilByPersona(personaEncontrada.get().getId());
            Perfil perfil = perfilEncontrado.get();
            perfil.setDescripcion(infoDTO.getDescripcion());
            perfil.setInfo_adicional(infoDTO.getInfo_adicional());
            return perfilDao.save(perfil);
        }else {
            throw new RuntimeException("No se pudo actualizar la informacion porque el usuario de codigo "+ codigo+" no se encontro") ;
        }
    }
}
