package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.dto.CrearHobbyDTO;
import utp.edu.models.dto.EliminarHobbyDTO;
import utp.edu.models.entities.Hobby;
import utp.edu.models.entities.PerfilHobby;
import utp.edu.services.IHobbyService;

import java.util.List;

@RestController
@RequestMapping("/api/hobby")
public class HobbyController {

    @Autowired
    IHobbyService hobbyService;

    @GetMapping("/lista/{codigo}")
    public List<Hobby> listHobbiesByPerson(@PathVariable String codigo) {
        return hobbyService.listHobbiesByCod(codigo);
    }

    @PostMapping("/crear/{codigo}")
    public ResponseEntity<PerfilHobby> crearHobby(@PathVariable String codigo, @RequestBody CrearHobbyDTO crearHobbyDTO) {
        try {
            crearHobbyDTO.setCodigoPersona(codigo);
            PerfilHobby nuevoHobby = hobbyService.crearHobby(crearHobbyDTO);
            return new ResponseEntity<>(nuevoHobby, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarHobby(@RequestBody EliminarHobbyDTO eliminarHobbyDTO) {
        try {
            hobbyService.deletePerfilHobby(eliminarHobbyDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
