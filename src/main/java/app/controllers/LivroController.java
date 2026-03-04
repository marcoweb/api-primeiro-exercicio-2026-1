package app.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import app.record.LivroDTO;
import app.services.LivroService;

@RestController
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping
    public Iterable<LivroDTO> getAll() {
        return livroService.findAll();
    }
}