package projeto.Spring.regesc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import projeto.Spring.regesc.Service.CrudAlunosService;
import projeto.Spring.regesc.Service.CrudDisciplinaService;
import projeto.Spring.regesc.Service.CrudProfessorService;

import java.util.Scanner;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner { // é uma interface no Spring Boot que permite executar código após o contexto da aplicação ser carregado, mas antes da aplicação começar a rodar
	private CrudProfessorService professorService;
	private CrudDisciplinaService disciplinaService;
	private CrudAlunosService alunosService;

	// objetos passados por parametro são injetados automaticamente pelo Sp1ring
	// porque classes possuem a anotação @Service
	public RegescApplication(CrudProfessorService professorService, CrudDisciplinaService disciplinaService, CrudAlunosService alunosService){
		this.professorService = professorService;
		this.disciplinaService = disciplinaService;
		this.alunosService = alunosService;
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
			System.out.println("2 - disciplina");
			System.out.println("3 - aluno");
			int opcao = box.nextInt();

			switch (opcao){
				case 1:
					this.professorService.menu(box);
					break;
				case 2:
					this.disciplinaService.menu(box);
					break;
				case 3:
					this.alunosService.menu(box);
					break;
				default:
					isTrue = false;
					break;
			}
		}
	}
}
