package utp.edu.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utp.edu.models.dto.InfoDTO;
import utp.edu.models.entities.Perfil;

import java.util.Optional;

@Repository
public interface PerfilDao extends JpaRepository<Perfil, Long> {

    @Query("SELECT new utp.edu.models.dto.InfoDTO(p.nombres, p.ap_paterno, p.ap_materno, pf.infoAdicional, pf.descripcion) FROM Perfil pf " +
            "JOIN Persona p ON p.id = pf.persona.id " +
            "WHERE p.codigo = :codigo")
    InfoDTO getInfoByCod(@Param("codigo") String codigo);

    @Query("SELECT pf FROM Perfil pf " +
            "WHERE pf.persona.id = :idPersona")
    Optional<Perfil> findPerfilByPersona(@Param("idPersona") Long idPersona);
}
