package br.com.tarefas.service;

import br.com.tarefas.entities.Tarefa;
import br.com.tarefas.exception.TarefaNotFound;
import br.com.tarefas.repository.TafefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TafefaRepository repository;

    public Tarefa recuperarTarefa(Long id) {
        Optional<Tarefa> tarefaOp = repository.findById(id);
        return tarefaOp.orElseThrow(() -> new TarefaNotFound("Tafefa com ID " + id + " não econtrada"));
    }

    public Tarefa adicionarTarefa(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public List<Tarefa> recuperaListDeTarefas() {
        return repository.findAll();
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefa) {
        Optional<Tarefa> tarefaOp = repository.findById(id);
        if (tarefaOp.isPresent()) {
            tarefa.setId(id);
            return repository.save(tarefa);

        }
        throw new TarefaNotFound("Tafefa com ID " + id + " não econtrada");
    }

    public void deletarTarefa(Long id) {
        if (!repository.existsById(id)){
            throw new TarefaNotFound("Tafefa com ID " + id + " não econtrada");
        }
        repository.deleteById(id);
    }

}
