package com.projeto.projetos.sustentaveis.service;

import com.projeto.projetos.sustentaveis.dto.UsuarioRequest;
import com.projeto.projetos.sustentaveis.dto.UsuarioResponse;
import com.projeto.projetos.sustentaveis.model.Usuario;
import com.projeto.projetos.sustentaveis.model.Usuario.Perfil;
import com.projeto.projetos.sustentaveis.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostConstruct
    public void criarUsuarioAdmin() {
        if (repository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setUsername("admin");
            admin.setSenha(encoder.encode("123456"));
            admin.setPerfil(Perfil.ADMIN);
            repository.save(admin);
        }
    }

    public UsuarioResponse create(UsuarioRequest request) {
        Usuario user = new Usuario();
        user.setNome(request.nome);
        user.setUsername(request.username);
        user.setSenha(encoder.encode(request.senha));
        user.setPerfil(request.perfil);
        return toResponse(repository.save(user));
    }

    public List<UsuarioResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public UsuarioResponse findById(Long id) {
        return toResponse(repository.findById(id).orElseThrow());
    }

    public UsuarioResponse update(Long id, UsuarioRequest request) {
        Usuario user = repository.findById(id).orElseThrow();
        user.setNome(request.nome);
        user.setUsername(request.username);
        user.setSenha(encoder.encode(request.senha));
        user.setPerfil(request.perfil);
        return toResponse(repository.save(user));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.id = usuario.getId();
        response.nome = usuario.getNome();
        response.username = usuario.getUsername();
        response.perfil = usuario.getPerfil();
        return response;
    }
}
