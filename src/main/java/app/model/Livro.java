package app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

import app.record.LivroDTO;

@Entity
@Table(name="livros")
@Getter
@Setter
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name="id_genero", nullable=false)
    private Genero genero;

    @ManyToMany
    @JoinTable(
        name="livros_possuem_autores",
        joinColumns = @JoinColumn(name="livro_id"),
        inverseJoinColumns = @JoinColumn(name="autor_id")
    )
    private List<Autor> autores = new ArrayList<Autor>();

    public Livro(LivroDTO dados) {
        this.id = dados.id();
        this.titulo = dados.titulo();
        this.genero = new Genero(dados.genero());
    }
}