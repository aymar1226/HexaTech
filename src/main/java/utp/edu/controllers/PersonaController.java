package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.models.dto.PersonaDTO;
import utp.edu.models.entities.Persona;
import utp.edu.services.IPersonaService;

import java.util.List;

@RestController
@RequestMapping("api/persona")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;


    @GetMapping("/lista/cursos/{idCurso}")
    public List<Persona> getPersonasByCurso(@PathVariable Long idCurso) {
        System.out.println(personaService.getPersonasByCurso(idCurso));
        return personaService.getPersonasByCurso(idCurso);
    }

    @GetMapping("/lista/grupos/{idGrupo}")
    public List<PersonaDTO> getPersonasByGrupo(@PathVariable Long idGrupo) {
        System.out.println(personaService.getPersonasByGrupo(idGrupo));
        return personaService.getPersonasByGrupo(idGrupo);
    }

    @GetMapping("/lider/{idGrupo}")
    public Persona getLiderByGroup (@PathVariable Long idGrupo){
        System.out.println(idGrupo);
        return personaService.getLiderByGroup(idGrupo);
    }


}
