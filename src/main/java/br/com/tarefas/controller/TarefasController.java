package br.com.tarefas.controller;

import br.com.tarefas.repository.TafefaRepository;
import br.com.tarefas.entities.Tarefa;
import br.com.tarefas.service.TarefaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("{id}")
    public ResponseEntity<?> recuperarTarefa(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tarefaService.recuperarTarefa(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionarTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.adicionarTarefa(tarefa));
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> recuperaListDeTarefas() {
        return ResponseEntity.ok(tarefaService.recuperaListDeTarefas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        try {
            return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefa));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTarefa(@PathVariable Long id) {
        try {
            tarefaService.deletarTarefa(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
