package utp.edu.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utp.edu.models.entities.DocenteCurso;

import java.util.Optional;

@Repository
public interface DocenteCursoDao extends JpaRepository<DocenteCurso, Long> {

    @Query("SELECT dc FROM DocenteCurso dc WHERE dc.curso.id = :idCurso")
    Optional<DocenteCurso> findByCursoId(@Param("idCurso") Long idCurso);
}
