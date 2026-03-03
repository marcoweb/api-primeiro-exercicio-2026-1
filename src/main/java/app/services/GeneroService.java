package app.services;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import app.model.Genero;
import app.record.GeneroDTO;
import app.record.GeneroInsertDTO;
import app.repository.GeneroRepository;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepo;

    public Iterable<GeneroDTO> findAll(){
        return generoRepo.findAll().stream().map(GeneroDTO::new).toList();
    }

    public GeneroDTO findOne(long id) {
        Optional<Genero> resultado = generoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Gênero não encontrado"
            );
        }

        return new GeneroDTO(resultado.get());
    }

    public GeneroDTO insert(GeneroInsertDTO dados) {
        Genero genero = new Genero();
        genero.setNome(dados.nome());
        return new GeneroDTO(generoRepo.save(genero));
    }

    public GeneroDTO update(long id, GeneroInsertDTO dados) {
        Optional<Genero> resultado = generoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Gênero não encontrado"
            );
        }

        resultado.get().setNome(dados.nome());
        return new GeneroDTO(generoRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!generoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Gênero não encontrado"
            );
        }
        generoRepo.deleteById(id);
    }
}