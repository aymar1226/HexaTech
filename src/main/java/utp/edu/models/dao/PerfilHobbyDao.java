package utp.edu.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utp.edu.models.entities.PerfilHobby;

import jakarta.transaction.Transactional;

@Repository
public interface PerfilHobbyDao extends JpaRepository<PerfilHobby, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM PerfilHobby ph WHERE ph.id = :idPerfilHobby")
    void deleteById(@Param("idPerfilHobby") Long idPerfilHobby);

    @Modifying
    @Transactional
    @Query("DELETE FROM PerfilHobby ph WHERE ph.perfil.persona.codigo = :codigoPersona AND ph.hobby.id = :idHobby")
    void deleteByCodigoAndHobby(@Param("codigoPersona") String codigoPersona, @Param("idHobby") Long idHobby);
}
