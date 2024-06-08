package utp.edu.services;


import utp.edu.models.dto.CrearHobbyDTO;
import utp.edu.models.dto.EliminarHabilidadDTO;
import utp.edu.models.dto.EliminarHobbyDTO;
import utp.edu.models.entities.Hobby;
import utp.edu.models.entities.PerfilHobby;

import java.util.List;

public interface IHobbyService {
    List<Hobby> listHobbiesByCod(String codigoPersona);

    public PerfilHobby crearHobby (CrearHobbyDTO crearHobbyDTO);

    public void deleteHobby (EliminarHobbyDTO hobbyDTO);

}
