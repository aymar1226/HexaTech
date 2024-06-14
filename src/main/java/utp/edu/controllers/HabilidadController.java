package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.dto.CrearHabilidadDTO;
import utp.edu.models.dto.EliminarHabilidadDTO;
import utp.edu.models.entities.Habilidad;
import utp.edu.models.entities.PerfilHabilidad;
import utp.edu.services.IHabilidadService;

import java.util.List;

@RestController
@RequestMapping("/api/habilidad")
public class HabilidadController {

    @Autowired
    IHabilidadService habilidadService;

    @GetMapping("/lista/{codigo}")
    public List<Habilidad> listAbilitiesByPerson(@PathVariable String codigo) {
        return habilidadService.listAbilitiesByCod(codigo);
    }

    @PostMapping("/crear/{codigo}")
    public ResponseEntity<PerfilHabilidad> crearHabilidad(@PathVariable String codigo, @RequestBody CrearHabilidadDTO crearHabilidadDTO) {
        try {
            crearHabilidadDTO.setCodigoPersona(codigo);  // Set the codigoPersona from the path variable
            PerfilHabilidad nuevaHabilidad = habilidadService.crearHabilidad(crearHabilidadDTO);
            return new ResponseEntity<>(nuevaHabilidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarHabilidad(@RequestBody EliminarHabilidadDTO eliminarHabilidadDTO) {
        try {
            habilidadService.deletePerfilHabilidad(eliminarHabilidadDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
