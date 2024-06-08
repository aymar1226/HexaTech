package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.dto.InfoDTO;
import utp.edu.models.entities.Perfil;
import utp.edu.services.IPerfilService;

@RestController
@RequestMapping("api/perfil")
public class PerfilController {
    @Autowired
    private IPerfilService perfilService;

    @GetMapping("/info/{codigo}")
    public InfoDTO getInfoByCod (@PathVariable String codigo){
        return perfilService.getInfoByCod(codigo);
    }

    @PutMapping("update/{codigo}")
    public ResponseEntity<Perfil> updateInfo(@PathVariable String codigo, @RequestBody InfoDTO infoDTO) {
        try {
            Perfil perfilModificado = perfilService.updateInfo(codigo,infoDTO);
            return new ResponseEntity<>(perfilModificado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
