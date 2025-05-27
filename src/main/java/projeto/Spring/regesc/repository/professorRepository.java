package projeto.Spring.regesc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projeto.Spring.regesc.Professor;

import java.util.Optional;

@Repository
public interface professorRepository extends CrudRepository<Professor, Long> {

}
