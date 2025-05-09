package com.projeto.projetos.sustentaveis.controller;

import com.projeto.projetos.sustentaveis.model.Organizacao;
import com.projeto.projetos.sustentaveis.service.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizacaoController {

    @Autowired
    private OrganizacaoService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Organizacao create(@RequestBody Organizacao organizacao) {
        return service.create(organizacao);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<Organizacao> list() {
        return service.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Organizacao> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Organizacao> update(@PathVariable Long id, @RequestBody Organizacao org) {
        return ResponseEntity.ok(service.update(id, org));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
