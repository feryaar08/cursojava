package com.redsoft.curso.cursojava.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.redsoft.curso.cursojava.modelo.Usuario;
import com.redsoft.curso.cursojava.servicio.UsuarioService;

@Controller
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAllUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/insertUsuarios")
    public ResponseEntity<String> insertUsuarios(@RequestBody Usuario usuario) {
        usuarioService.agregarUsuario(usuario);
        return ResponseEntity.ok("Usuario agregado con exito");
    }

    @PostMapping("/borrarUsuarios")
    public ResponseEntity<String> borrarUsuarios(@RequestBody Map<String, String> body) {
        String usuarioId = body.get("usuarioId");
        usuarioService.borrarUsuario(Integer.parseInt(usuarioId));
        return ResponseEntity.ok("Usuario eliminado con exito");
    }

}
