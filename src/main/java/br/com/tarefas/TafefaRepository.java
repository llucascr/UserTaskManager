package br.com.tarefas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TafefaRepository extends JpaRepository<Tarefa, Long> {
}
