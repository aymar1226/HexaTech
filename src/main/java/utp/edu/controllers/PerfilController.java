package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.dto.ActualizarInfoAdicionalDTO;
import utp.edu.models.dto.InfoDTO;
import utp.edu.services.IPerfilService;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {

    @Autowired
    private IPerfilService perfilService;

    @GetMapping("/info/{codigo}")
    public ResponseEntity<InfoDTO> getInfo(@PathVariable String codigo) {
        try {
            InfoDTO infoDTO = perfilService.getInfo(codigo);
            return new ResponseEntity<>(infoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar-info-adicional/{codigo}")
    public ResponseEntity<Void> actualizarInfoAdicional(@PathVariable String codigo, @RequestBody ActualizarInfoAdicionalDTO actualizarInfoAdicionalDTO) {
        try {
            actualizarInfoAdicionalDTO.setCodigoPersona(codigo);
            perfilService.actualizarInfoAdicional(actualizarInfoAdicionalDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
