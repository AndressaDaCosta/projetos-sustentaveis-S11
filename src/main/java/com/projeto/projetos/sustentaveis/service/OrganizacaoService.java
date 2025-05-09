package com.projeto.projetos.sustentaveis.service;

import com.projeto.projetos.sustentaveis.model.Organizacao;
import com.projeto.projetos.sustentaveis.repository.OrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacaoService {

    @Autowired
    private OrganizacaoRepository repository;

    public Organizacao create(Organizacao organizacao) {
        return repository.save(organizacao);
    }

    public List<Organizacao> findAll() {
        return repository.findAll();
    }

    public Organizacao findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Organizacao update(Long id, Organizacao updated) {
        Organizacao org = findById(id);
        org.setNome(updated.getNome());
        org.setContato(updated.getContato());
        return repository.save(org);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
