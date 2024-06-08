package utp.edu.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utp.edu.models.entities.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario,Long> {


    @Query("SELECT u FROM Usuario u WHERE u.persona.codigo = :codigo AND u.password = :password")
    Optional<Usuario> findByPersonaCodigoAndPassword(@Param("codigo") String codigo, @Param("password") String password);

}
