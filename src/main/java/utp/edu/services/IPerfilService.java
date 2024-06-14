package utp.edu.services;

import utp.edu.models.dto.ActualizarDescripcionDTO;
import utp.edu.models.dto.ActualizarInfoAdicionalDTO;
import utp.edu.models.dto.InfoDTO;

public interface IPerfilService {
    InfoDTO getInfo(String codigoPersona);
    void actualizarInfoAdicional(ActualizarInfoAdicionalDTO actualizarInfoAdicionalDTO);
    void actualizarDescripcion(ActualizarDescripcionDTO actualizarDescripcionDTO);
}
