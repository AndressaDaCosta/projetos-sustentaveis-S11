package com.projeto.projetos.sustentaveis.controller;

import com.projeto.projetos.sustentaveis.dto.LoginRequest;
import com.projeto.projetos.sustentaveis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest login) {
        var usuario = repository.findByUsername(login.username);
        if (usuario.isPresent() && encoder.matches(login.senha, usuario.get().getSenha())) {
            return ResponseEntity.ok("Login efetuado com sucesso!");
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
