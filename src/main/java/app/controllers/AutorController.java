package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.record.AutorDTO;
import app.record.AutorInsertDTO;
import app.services.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping
    public Iterable<AutorDTO> getAll() {
        return autorService.getAll();
    }

    @PostMapping
    public AutorDTO insert(@RequestBody AutorInsertDTO dados) {
        return autorService.insert(dados);
    }
}