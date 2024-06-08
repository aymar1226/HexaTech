package utp.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.models.dto.CredsDTO;
import utp.edu.services.IUsuarioService;

@RestController
@RequestMapping("api/")
public class AuthController {
    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredsDTO creds) {
        return usuarioService.authenticate(creds)
                .map(usuario -> ResponseEntity.ok("Login successful"))
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }

}
