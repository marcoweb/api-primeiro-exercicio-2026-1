package app.services;

import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import app.model.Genero;
import app.model.Livro;
import app.model.Autor;
import app.record.LivroDTO;
import app.record.LivroInsertDTO;
import app.repository.AutorRepository;
import app.repository.GeneroRepository;
import app.repository.LivroRepository;
import app.services.GeneroService;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepo;
    @Autowired
    private GeneroService generoService;
    // private GeneroRepository generoRepo;
    @Autowired
    private AutorService autorService;

    public Iterable<LivroDTO> findAll() {
        return livroRepo.findAll().stream().map(LivroDTO::new).toList();
    }

    public LivroDTO insert(LivroInsertDTO dados) {
        // Optional<Genero> resultado = generoRepo.findById(dados.id_genero());
        
        // if(resultado.isEmpty()) {
        //     throw new ResponseStatusException(
        //         HttpStatus.NOT_FOUND, "Gênero Não Encontrado"
        //     );
        // }

        Genero genero = new Genero(generoService.findOne(dados.id_genero()));

        Livro livro = new Livro();
        livro.setTitulo(dados.titulo());
        livro.setGenero(genero);
        for(long autor_id : dados.autores()) {
            livro.getAutores().add(new Autor(autorService.getOne(autor_id)));
        }

        return new LivroDTO(livroRepo.save(livro));
    }

    public LivroDTO update(long id, LivroInsertDTO dados) {
        Optional<Livro> resultado = livroRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro Não Encontrado"
            );
        }

        Genero genero = new Genero(generoService.findOne(dados.id_genero()));
        resultado.get().setTitulo(dados.titulo());
        resultado.get().setGenero(genero);
        resultado.get().setAutores(new ArrayList());
        for(long id_autor : dados.autores()) {
            resultado.get().getAutores().add(new Autor(autorService.getOne(autor_id)));
        }
        return new LivroDTO(livroRepo.save(resultado.get()));
    }
}