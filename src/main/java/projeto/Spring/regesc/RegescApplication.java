package projeto.Spring.regesc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import projeto.Spring.regesc.Service.CrudProfessorService;
import projeto.Spring.regesc.repository.professorRepository;

import java.util.Scanner;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner {
	private CrudProfessorService professorService;

	// objetos passados por parametro são injetados automaticamente pelo Spring
	// pq classes possuem a anotação @Service
	public RegescApplication(CrudProfessorService professorService){
 		this.professorService = professorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner box = new Scanner(System.in);

		while (isTrue){
			System.out.println("Qual entidadade voce deseja interagir?");
			System.out.println("0 - sair");
			System.out.println("1 - professor");
			int opcao = box.nextInt();

			switch (opcao){
				case 1:
					this.professorService.menu(box);
					break;
				default:
					isTrue = false;
					break;

			}
		}
	}
}
