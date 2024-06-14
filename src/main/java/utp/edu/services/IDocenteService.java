package utp.edu.services;

import org.springframework.data.repository.query.Param;
import utp.edu.models.entities.Curso;
import utp.edu.models.entities.Grupo;

import java.util.List;

public interface IDocenteService {
    List<Curso> getCursosByDocente(String codigoDocente);
    List<Grupo> getGruposByCursoDocente(Long idCurso, String codigoDocente);

}
