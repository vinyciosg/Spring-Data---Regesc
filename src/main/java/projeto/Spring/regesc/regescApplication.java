package projeto.Spring.regesc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import projeto.Spring.regesc.repository.professorRepository;

@SpringBootApplication
public class regescApplication implements CommandLineRunner {
	private professorRepository repository;

	public regescApplication(professorRepository repository){
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(regescApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		professor professor  = new professor("Gustavo", "xyz");
		System.out.println("Professor antes do save (percistencia com o BD)");
		System.out.println(professor);

		this.repository.save(professor);

		System.out.println("Professor depois do save (percistencia com o BD)");
		System.out.println(professor);
	}
}
