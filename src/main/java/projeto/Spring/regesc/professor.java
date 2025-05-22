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
    private String aprontuario;
}
