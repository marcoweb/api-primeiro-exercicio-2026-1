package app.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import app.model.Autor;
import app.record.AutorDTO;
import app.record.AutorInsertDTO;
import app.repository.AutorRepository;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepo;

    public Iterable<AutorDTO> getAll() {
        return this.autorRepo.findAll().stream().map(AutorDTO::new).toList();
    }

    public AutorDTO getOne(long id) {
        Optional<Autor> resultado = autorRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Autor Não Encontrado"
            );
        }

        return new AutorDTO(resultado.get());
    }

    public AutorDTO insert(AutorInsertDTO dados) {
        Autor autor = new Autor();
        autor.setNome(dados.nome());

        return new AutorDTO(autorRepo.save(autor));
    }
}