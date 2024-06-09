package utp.edu.services;

import utp.edu.models.dto.CrearHabilidadDTO;
import utp.edu.models.entities.Habilidad;
import utp.edu.models.entities.PerfilHabilidad;

import java.util.List;

public interface IHabilidadService {
    List<Habilidad> listAbilitiesByCod(String codigoPersona);
    PerfilHabilidad crearHabilidad(CrearHabilidadDTO crearHabilidadDTO);
    void deletePerfilHabilidad(Long idPerfilHabilidad);
}
