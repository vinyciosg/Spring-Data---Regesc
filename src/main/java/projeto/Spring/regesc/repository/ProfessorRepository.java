package projeto.Spring.regesc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projeto.Spring.regesc.orm.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}
