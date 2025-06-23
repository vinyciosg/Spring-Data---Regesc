package projeto.Spring.regesc.repository;

import org.springframework.data.repository.CrudRepository;
import projeto.Spring.regesc.orm.Aluno;

import java.util.List;

public interface AlunoRepository extends CrudRepository<Aluno, Long>{

    List<Aluno> findByNomeStartingWith(String nome);
    List<Aluno> findByNomeStartingWithAndIdadeLessThanEqual(String nome, Integer idade);

}
