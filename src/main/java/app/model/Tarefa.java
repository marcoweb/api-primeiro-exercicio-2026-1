package app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String descricao;

    public Tarefa(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}