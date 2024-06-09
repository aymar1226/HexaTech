package utp.edu.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utp.edu.models.entities.MiembroGrupo;

import java.util.Optional;

public interface MiembroGrupoDao extends JpaRepository<MiembroGrupo, Long> {
    @Query("SELECT mg FROM MiembroGrupo mg WHERE mg.grupo.id = :idGrupo AND mg.persona.codigo = :codigoPersona")
    Optional<MiembroGrupo> findByGrupoIdAndPersonaCodigo(@Param("idGrupo") Long idGrupo, @Param("codigoPersona") String codigoPersona);
}