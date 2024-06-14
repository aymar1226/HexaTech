package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.DocenteCursoDao;
import utp.edu.models.entities.Curso;
import utp.edu.models.entities.Grupo;

import java.util.List;

@Service
public class DocenteServiceImpl implements IDocenteService{

    @Autowired
    private DocenteCursoDao docenteCursoDao;

    @Override
    public List<Curso> getCursosByDocente(String codigoDocente) {
        return docenteCursoDao.getCursosByDocente(codigoDocente);
    }

    @Override
    public List<Grupo> getGruposByCursoDocente(Long idCurso, String codigoDocente) {
        return docenteCursoDao.getGruposByCursoDocente(idCurso,codigoDocente);
    }
}
