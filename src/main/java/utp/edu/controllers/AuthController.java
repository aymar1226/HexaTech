package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.dto.CredsDTO;
import utp.edu.models.dto.LoginResponse;
import utp.edu.services.IUsuarioService;

@RestController
@RequestMapping("api/")
public class AuthController {
    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody CredsDTO creds) {
        System.out.println(creds);

        return usuarioService.authenticate(creds)
                .map(usuario -> ResponseEntity.ok(new LoginResponse(usuario.getRol())))
                .orElseGet(() -> ResponseEntity.status(401).body(new LoginResponse("Invalid credentials")));
    }

}
