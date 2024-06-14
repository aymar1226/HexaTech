package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.models.entities.Curso;
import utp.edu.models.entities.Grupo;
import utp.edu.services.IDocenteService;

import java.util.List;

@RestController
@RequestMapping("api/docente")
public class DocenteController {

    @Autowired
    private IDocenteService docenteService;

    @GetMapping("/cursos/{codigoDocente}")
    List<Curso> getCursosByDocente(@PathVariable String codigoDocente){
        return docenteService.getCursosByDocente(codigoDocente);
    }

    @GetMapping("/curso/grupos/{codigoDocente}/{idCurso}")
    List<Grupo> getGruposByCursoDocente(@PathVariable Long idCurso, @PathVariable String codigoDocente){
        return docenteService.getGruposByCursoDocente(idCurso,codigoDocente);
    }


}
