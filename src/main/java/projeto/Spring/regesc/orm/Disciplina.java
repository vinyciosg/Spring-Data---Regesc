package projeto.Spring.regesc.orm;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private int semestre;

    @ManyToOne // Muitos para um
    @JoinColumn(name = "professor_id" , nullable = true) // Coluna de junção - por padrão ele pega o ID
    private Professor professor;


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

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", semestre=" + semestre +
                ", professor=" + professor +
                '}';
    }
}
