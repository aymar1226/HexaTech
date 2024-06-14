package utp.edu.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utp.edu.models.entities.Curso;
import utp.edu.models.entities.DocenteCurso;
import utp.edu.models.entities.Grupo;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocenteCursoDao extends JpaRepository<DocenteCurso, Long> {

    @Query("SELECT dc FROM DocenteCurso dc WHERE dc.curso.id = :idCurso")
    Optional<DocenteCurso> findByCursoId(@Param("idCurso") Long idCurso);


    @Query("SELECT c FROM Curso c " +
            "JOIN DocenteCurso dc ON c.id = dc.curso.id " +
            "WHERE dc.docente.persona.codigo = :codigo")
    List<Curso> getCursosByDocente(@Param("codigo") String codigoDocente);


    @Query("SELECT g FROM Grupo g " +
            "JOIN Curso c ON c.id = g.curso.id " +
            "JOIN DocenteCurso dc ON c.id = dc.curso.id " +
            "WHERE dc.curso.id = :idCurso AND dc.docente.persona.codigo = :codigoDocente")
    List<Grupo> getGruposByCursoDocente(@Param("idCurso") Long idCurso,@Param("codigoDocente") String codigoDocente);


}
