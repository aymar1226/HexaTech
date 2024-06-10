package utp.edu.models.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utp.edu.models.entities.Grupo;
import utp.edu.models.entities.Habilidad;
import utp.edu.models.entities.PerfilHabilidad;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabilidadDao extends JpaRepository<Habilidad,Long> {

    @Query("SELECT h FROM Habilidad h " +
            "JOIN PerfilHabilidad ph ON h.id = ph.habilidad.id " +
            "JOIN Perfil pf ON pf.id = ph.perfil.id " +
            "JOIN Persona p ON p.id = pf.persona.id " +
            "WHERE p.codigo = :codigo")
    List<Habilidad> listAbilitiesByCod(@Param("codigo") String codigoPersona);

    @Query("SELECT ph FROM PerfilHabilidad ph " +
            "JOIN ph.perfil pf " +
            "JOIN pf.persona p " +
            "WHERE p.codigo = :codigo AND ph.habilidad.id = :idHabilidad")
    Optional<PerfilHabilidad> findPerfilHabilidad(@Param("codigo") String codigoPersona, @Param("idHabilidad") Long idHabilidad);


    @Modifying
    @Query("DELETE FROM PerfilHabilidad ph " +
            "WHERE ph.perfil.persona.codigo = :codigoPersona AND ph.habilidad.id = :idHabilidad")
    @Transactional
    void deleteHabilidad(@Param("codigoPersona") String codigoPersona, @Param("idHabilidad") Long idHabilidad);

}
