package com.projeto.projetos.sustentaveis.controller;

import com.projeto.projetos.sustentaveis.config.JwtUtil;
import com.projeto.projetos.sustentaveis.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.username, login.senha));
            String token = jwtUtil.generateToken(login.username);
            return ResponseEntity.ok().body(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

}
