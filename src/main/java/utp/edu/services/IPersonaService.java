package utp.edu.services;

import utp.edu.models.dto.PersonaDTO;
import utp.edu.models.entities.Persona;

import java.util.List;

public interface IPersonaService {

    public List<Persona> getPersonasByCurso(Long idCurso);
    public List<PersonaDTO> getPersonasByGrupo(Long idGrupo);
    public Persona getLiderByGroup(Long idGrupo);

}
