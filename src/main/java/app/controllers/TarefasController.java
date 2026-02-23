package app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;

import app.model.Tarefa;
import app.repository.TarefaRepository;

import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    @Autowired
    private TarefaRepository tarefaRepo;

    @GetMapping
    public Iterable<Tarefa> list() {
        return tarefaRepo.findAll();
    }

    @GetMapping("/{id}")
    public Tarefa getOne(@PathVariable long id) {
        return tarefaRepo.findById(id).get();
    }

    @PostMapping
    public Tarefa insert(@RequestBody Tarefa novaTarefa) {
        return tarefaRepo.save(novaTarefa);
    }

    @PutMapping("/{id}")
    public Tarefa update(@PathVariable long id, @RequestBody Tarefa modif){
        Optional<Tarefa> busca = tarefaRepo.findById(id);
        busca.get().setDescricao(modif.getDescricao());
        return tarefaRepo.save(busca.get());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        tarefaRepo.deleteById(id);
    }
}