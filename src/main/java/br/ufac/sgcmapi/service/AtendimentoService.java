package br.ufac.sgcmapi.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatusAtendimento;
import br.ufac.sgcmapi.repository.AtendimentoRepository;

@Service
public class AtendimentoService implements ICrudService<Atendimento> {

    private final AtendimentoRepository repo;

    @Autowired
    public AtendimentoService(AtendimentoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Atendimento> getAll() {
        return repo.findAll();
    }

    @Override
    public Atendimento getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Atendimento> getByAll(String termoBusca) {
        return repo.findByAll(termoBusca);
    }
    
    public List<String> getByAgendados(Long profissional,Date data) {
        return repo.getByAgendados(profissional, data);
    }

    @Override
    public Atendimento save(Atendimento objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        Atendimento registro = repo.getById(id);
        registro.setStatus(EStatusAtendimento.CANCELADO);
        repo.save(registro);
    }

    public Atendimento updateStatus(Long id) {
        Atendimento registro = repo.getById(id);
        registro.setStatus(registro.getStatus().next());
        registro = repo.save(registro);
        return registro;
    }
    
}
