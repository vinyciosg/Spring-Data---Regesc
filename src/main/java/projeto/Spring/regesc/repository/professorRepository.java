package projeto.Spring.regesc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projeto.Spring.regesc.professor;

@Repository
public interface professorRepository extends CrudRepository<professor, Long> {
}
