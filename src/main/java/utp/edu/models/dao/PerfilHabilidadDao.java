package utp.edu.models.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utp.edu.models.entities.PerfilHabilidad;

@Repository
public interface PerfilHabilidadDao extends JpaRepository<PerfilHabilidad, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM PerfilHabilidad ph WHERE ph.id = :idPerfilHabilidad")
    void deleteById(@Param("idPerfilHabilidad") Long idPerfilHabilidad);
}
