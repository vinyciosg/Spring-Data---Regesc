package projeto.Spring.regesc;

import jakarta.persistence.*;
import org.hibernate.boot.registry.selector.spi.StrategyCreator;

@Entity
@Table(name = "professores")
public class professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String prontuario;

    @Deprecated // para indicar que não será muito usada
    public professor() {
    }

    public professor(String nome, String prontuario) {
        this.nome = nome;
        this.prontuario = prontuario;
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

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
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
