package utp.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.models.dao.UsuarioDao;
import utp.edu.models.dto.CredsDTO;
import utp.edu.models.entities.Usuario;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public Optional<Usuario> authenticate(CredsDTO creds) {

        String codigo=creds.getCodUsuario();
        String password=creds.getPassword();
        return usuarioDao.findByPersonaCodigoAndPassword(codigo,password);
    }
}
