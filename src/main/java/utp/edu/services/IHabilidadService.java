package utp.edu.services;

import utp.edu.models.dto.CrearHabilidadDTO;
import utp.edu.models.dto.EliminarHabilidadDTO;
import utp.edu.models.entities.Habilidad;
import utp.edu.models.entities.PerfilHabilidad;

import java.util.List;

public interface IHabilidadService {
    public List<Habilidad> listAbilitiesByCod(String codigoPersona);

    public PerfilHabilidad crearHabilidad (CrearHabilidadDTO crearHabilidadDTO);

    public void deleteHabilidad (EliminarHabilidadDTO HabilidadDTO);
}
