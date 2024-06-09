package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.dto.MiembroDTO;
import utp.edu.models.dto.CrearGrupoDTO;
import utp.edu.models.dto.UpdateMiembroDTO;
import utp.edu.models.entities.Grupo;
import utp.edu.models.entities.MiembroGrupo;
import utp.edu.services.IGrupoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/grupo")
public class GrupoController {
    @Autowired
    private IGrupoService grupoService;

    @GetMapping("/lista/{codigo}")
    public List<Grupo> getGruposByCodPersona(@PathVariable String codigo) {
        return grupoService.getGruposByCodPersona(codigo);
    }

    @PostMapping("/crear")
    public ResponseEntity<Grupo> crearGrupo(@RequestBody CrearGrupoDTO grupoDTO){
        try {
            Grupo nuevoGrupo = grupoService.crearGrupo(grupoDTO);
            return new ResponseEntity<>(nuevoGrupo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("agregar/miembro")
    public ResponseEntity<MiembroGrupo> agregarMiembroAlGrupo(@RequestBody MiembroDTO miembroDTO){
        System.out.println(miembroDTO);
        try {
            miembroDTO.setIdGrupo(miembroDTO.getIdGrupo());
            MiembroGrupo miembroAgregado = grupoService.agregarMiembroAlGrupo(miembroDTO);
            return new ResponseEntity<>(miembroAgregado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/eliminar/miembro")
    public ResponseEntity<Void> eliminarMiembro(@RequestBody MiembroDTO miembroDTO){
        try {
            grupoService.deleteMiembro(miembroDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/miembro")
    public ResponseEntity<MiembroGrupo> updateProducto(@RequestBody UpdateMiembroDTO updateMiembroDTO) {
        try {
            MiembroGrupo miembroModificado = grupoService.updateMiembro(updateMiembroDTO);
            return new ResponseEntity<>(miembroModificado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rol/{grupoId}/{codigoPersona}")
    public ResponseEntity<Map<String, String>> getRolInGroup(@PathVariable Long grupoId, @PathVariable String codigoPersona) {
        String rol = grupoService.getRolInGroup(grupoId, codigoPersona);
        Map<String, String> response = new HashMap<>();
        response.put("rol", rol);
        return ResponseEntity.ok(response);
    }

}
