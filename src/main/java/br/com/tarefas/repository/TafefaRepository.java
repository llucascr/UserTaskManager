package br.com.tarefas.repository;

import br.com.tarefas.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TafefaRepository extends JpaRepository<Tarefa, Long> {
}
