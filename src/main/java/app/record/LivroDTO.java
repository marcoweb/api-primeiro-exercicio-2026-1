package app.record;

import java.util.List;

import app.model.Livro;
import app.record.AutorDTO;

public record LivroDTO (
    long id,
    String titulo,
    GeneroDTO genero,
    List<AutorDTO> autores
) {
    public LivroDTO(Livro dados) {
        this(
            dados.getId(),
            dados.getTitulo(),
            new GeneroDTO(dados.getGenero()),
            dados.getAutores().stream().map(AutorDTO::new).toList()
        );
    }
}