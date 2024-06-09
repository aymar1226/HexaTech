package utp.edu.services;

import utp.edu.models.dto.CredsDTO;
import utp.edu.models.entities.Usuario;

import java.util.Optional;

public interface IUsuarioService {
    public Optional<Usuario> authenticate (CredsDTO creds);
}
