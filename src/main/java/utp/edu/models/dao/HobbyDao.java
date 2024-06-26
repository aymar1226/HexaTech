package utp.edu.models.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utp.edu.models.entities.Hobby;
import utp.edu.models.entities.PerfilHobby;

import java.util.List;
import java.util.Optional;

@Repository
public interface HobbyDao extends JpaRepository<Hobby, Long> {

    @Query("SELECT h FROM Hobby h " +
            "JOIN PerfilHobby ph ON h.id = ph.hobby.id " +
            "JOIN Perfil pf ON pf.id = ph.perfil.id " +
            "JOIN Persona p ON p.id = pf.persona.id " +
            "WHERE p.codigo = :codigo")
    List<Hobby> listHobbiesByCod(@Param("codigo") String codigoPersona);

    @Query("SELECT ph FROM PerfilHobby ph " +
            "JOIN ph.perfil pf " +
            "JOIN pf.persona p " +
            "WHERE p.codigo = :codigo AND ph.hobby.id = :idHobby")
    Optional<PerfilHobby> findPerfilHobby(@Param("codigo") String codigoPersona, @Param("idHobby") Long idHobby);

    @Modifying
    @Query("DELETE FROM PerfilHobby ph " +
            "WHERE ph.perfil.persona.codigo = :codigoPersona AND ph.hobby.id = :idHobby")
    @Transactional
    void deleteHobby(@Param("codigoPersona") String codigoPersona, @Param("idHobby") Long idHobby);
}
