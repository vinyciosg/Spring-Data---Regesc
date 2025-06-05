package projeto.Spring.regesc.orm;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_professores")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String prontuario;

    // está sendo mapeada pelo atributo professor
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY) // // fetch = esta fazendo uma busca EAGER: tras os dados / LAZY: é preguiçoso não tras os dados por padrão
    private List<Disciplina> disciplinas = new ArrayList<>();

    @Deprecated // para indicar que não será muito usadax
    public Professor() {
    }

    public Professor(String nome, String prontuario) {
        this.nome = nome;
        this.prontuario = prontuario;
    }

    public Long getId() {return id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getProntuario() {return prontuario;}

    public void setProntuario(String prontuario) {this.prontuario = prontuario;}

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @PreRemove
    public void atualizarDisciplina(){
        System.out.println("**** atualizarDisciplinasOnRemove ****");
        for (Disciplina disciplina : this.getDisciplinas()){
            disciplina.setProfessor(null);
        }
    }

    @Override
    public String toString() {
        return "professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", prontuario='" + prontuario + '\'' +
                '}';
    }
}
