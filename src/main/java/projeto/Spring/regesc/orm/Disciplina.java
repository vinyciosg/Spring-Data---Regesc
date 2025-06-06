package projeto.Spring.regesc.orm;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private int semestre;

    @ManyToOne(fetch = FetchType.EAGER) // Muitos para um
    @JoinColumn(name = "professor_id" , nullable = true) // Coluna de junção - por padrão ele pega o ID
    private Professor professor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_disciplinas_alunos",
            joinColumns = @JoinColumn(name = "disciplina_fk"),
            inverseJoinColumns = @JoinColumn(name = "aluno_fk"))
    private Set<Aluno> alunos;

    @Deprecated // não será utilizado
    public Disciplina() {}

    public Disciplina(String nome, int semestre, Professor professor) {
        this.nome = nome;
        this.semestre = semestre;
        this.professor = professor;
    }

    public Disciplina(String nome, int semestre) {
        this.nome = nome;
        this.semestre = semestre;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Set<Aluno> getAlunos() {return alunos;}

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", semestre=" + semestre +
                ", professor=" + professor +
                ", alunos=" + alunos +
                '}';
    }
}

