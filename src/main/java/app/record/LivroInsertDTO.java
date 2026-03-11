package app.record;

import java.util.List;

public record LivroInsertDTO(
    String titulo,
    long id_genero,
    List<Long> autores
) {
}