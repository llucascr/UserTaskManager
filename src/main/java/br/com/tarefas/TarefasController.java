package br.com.tarefas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    private List<Tarefa> listaDeTarefas= new ArrayList<>();

    @GetMapping("{id}")
    public ResponseEntity<Tarefa> recuperarTarefa(@PathVariable Long id) {
        Optional<Tarefa> tarefa = this.listaDeTarefas.stream()
                .filter(t -> t.getId() == id).findFirst();
        return tarefa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> adicionarTarefa(@RequestBody Tarefa tarefa) {

        boolean existe = listaDeTarefas.stream().filter(t ->
                t.getId() == tarefa.getId()
        ).findFirst().isPresent();

        if (!existe) {
            return ResponseEntity.badRequest().body(
                    Map.of("menssagem", "Tarefa com o ID " + tarefa.getId() + " ja existente na lista")
            );
        }

        this.listaDeTarefas.add(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> recuperaListDeTarefas() {
        return ResponseEntity.ok(this.listaDeTarefas);
    }

}
