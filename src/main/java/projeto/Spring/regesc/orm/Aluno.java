package projeto.Spring.regesc.orm;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    private Integer idade;

    @ManyToMany(mappedBy = "alunos")
    private Set<Disciplina> disciplinas;

    @Deprecated
    public Aluno() {
    }

    public Aluno(String nome, Integer idade, Set<Disciplina> disciplinas) {
        this.nome = nome;
        this.idade = idade;
        this.disciplinas = disciplinas;
    }

    public Long getId() {return id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public Integer getIdade() {return idade;}

    public void setIdade(Integer idade) {this.idade = idade;}

    public Set<Disciplina> getDisciplinas() {return disciplinas;}

    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
