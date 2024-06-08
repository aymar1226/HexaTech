package utp.edu.services;

import utp.edu.models.dto.InfoDTO;
import utp.edu.models.dto.UpdateInfoDTO;
import utp.edu.models.entities.Perfil;

public interface IPerfilService {
    public InfoDTO getInfoByCod(String codigoPersona);

    public Perfil updateInfo(String codigo,InfoDTO infoDTO);

}
