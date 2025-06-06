package projeto.Spring.regesc.repository;

import org.springframework.data.repository.CrudRepository;
import projeto.Spring.regesc.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long>{
}
