package projeto.Spring.regesc.Service;

import org.springframework.stereotype.Service;
import projeto.Spring.regesc.orm.Disciplina;
import projeto.Spring.regesc.orm.Professor;
import projeto.Spring.regesc.repository.DisciplinaRepository;
import projeto.Spring.regesc.repository.ProfessorRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudDisciplinaService {

    private DisciplinaRepository disciplinaRepository;
    private ProfessorRepository professorRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner){
        Boolean istrue = true;
        while (istrue){
            System.out.println("\nQual ação voce quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar uma disciplina");
            System.out.println("2 - Atualizar uma disciplina");
            System.out.println("3 - Visualizar todas as disciplinas");
            System.out.println("4 - Deletar uma disciplina");
            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    this.cadastrarDisciplina(scanner);
                    break;
                case 2:
                    this.atualizarDisciplina(scanner);
                    break;
                case 3:
                    this.visualizarDisciplina();
                    break;
                case 4:
                    this.deletarDisciplina(scanner);
                    break;
                default:
                    istrue = false;
                    break;
            }
        }
    }

    private void cadastrarDisciplina(Scanner scanner){
        System.out.print("Nome da disciplina: ");
        String nome = scanner.next();

        System.out.print("Semestre: ");
        int semestre = scanner.nextInt();

        System.out.print("Professor ID: ");
        Long professorID = scanner.nextLong();

        Optional<Professor> optional = this.professorRepository.findById(professorID);
        if (optional.isPresent()){

            Professor professor = optional.get();
            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            disciplinaRepository.save(disciplina);
            System.out.println("Salvo\n");
        }
        else {
            System.out.println("Professor ID " + professorID + " Invalido");
        }
    }

    private void atualizarDisciplina(Scanner scanner){
        System.out.print("Digite o ID da disciplina a ser atualizado: ");
        Long id = scanner.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

        if(optionalDisciplina.isPresent()){
            Disciplina disciplina = optionalDisciplina.get();

            System.out.print("Nome da Disciplina:");
            String nome = scanner.next();

            System.out.print("Semestre: ");
            int semestre = scanner.nextInt();

            System.out.print("Professor ID: ");
            Long disciplinaid = scanner.nextLong();

            Optional<Professor> optionalProfessor = this.professorRepository.findById(disciplinaid);
            if (optionalProfessor.isPresent()){
                Professor professor = optionalProfessor.get();

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);

                disciplinaRepository.save(disciplina);
                System.out.println("Atualizado\n");
            }
            else {
                System.out.println("Professor ID " + id + " Invalido");
            }
        }
        else {
            System.out.println("EROO!!!");
        }
    }

    private void visualizarDisciplina(){
        Iterable<Disciplina> disciplinas = disciplinaRepository.findAll();

        for (Disciplina disciplina : disciplinas ){
            System.out.println(disciplina);
        }
        System.out.println();
    }

    private void deletarDisciplina(Scanner scanner){
        System.out.print("Disciplina ID: ");
        Long id = scanner.nextLong();
        this.disciplinaRepository.deleteById(id);
        System.out.println("Discipplina deletada!\n");
    }
}
