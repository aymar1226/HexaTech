package utp.edu.services;

import utp.edu.models.entities.Curso;

import java.util.List;

public interface ICursoService {
    List<Curso> getCursosByCod(String codigo);
}
