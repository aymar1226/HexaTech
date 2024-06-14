package utp.edu.services;

import utp.edu.models.dto.CrearHobbyDTO;
import utp.edu.models.dto.EliminarHobbyDTO;
import utp.edu.models.entities.Hobby;
import utp.edu.models.entities.PerfilHobby;

import java.util.List;

public interface IHobbyService {
    List<Hobby> listHobbiesByCod(String codigoPersona);
    PerfilHobby crearHobby(CrearHobbyDTO crearHobbyDTO);
    void deletePerfilHobby(EliminarHobbyDTO eliminarHobbyDTO);
}
