package projeto.Spring.regesc.Service;

import org.springframework.stereotype.Service;
import projeto.Spring.regesc.orm.Aluno;
import projeto.Spring.regesc.orm.Disciplina;
import projeto.Spring.regesc.orm.Professor;
import projeto.Spring.regesc.repository.AlunoRepository;
import projeto.Spring.regesc.repository.DisciplinaRepository;
import projeto.Spring.regesc.repository.ProfessorRepository;

import java.util.*;

@Service
public class CrudDisciplinaService {

    private DisciplinaRepository disciplinaRepository;
    private ProfessorRepository professorRepository;
    private AlunoRepository alunoRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
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
            System.out.println("5 - Matricular alunos");
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
                case 5:
                    this.matricularAlunos(scanner);
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

            List<Aluno> alunos = this.matricular(scanner);

            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            disciplina.setAlunos((Set<Aluno>) alunos);

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

                List<Aluno> alunos = this.matricular(scanner);

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);
                disciplina.setAlunos((Set<Aluno>) alunos);

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

    private List<Aluno> matricular(Scanner scanner){
        Boolean istrue = true;
        List<Aluno> alunos = new ArrayList<Aluno>();

        while (istrue){
            System.out.println("ID do aluno a ser matriculado (0 para sair)");
            Long alunoID = scanner.nextLong();

            if (alunoID > 0){
                System.out.println("alunoID: " + alunoID);
                Optional<Aluno> optional = this.alunoRepository.findById(alunoID);

                if (optional.isPresent()){
                    alunos.add(optional.get());
                }
                else {
                    System.out.println("Nenhum aluno possui ID" + alunoID + "!");
                }
            }
            else{
                istrue = false;
            }
        }
        return alunos;
    }

    private void matricularAlunos(Scanner scanner){
        System.out.print("Digite o ID da disciplina para matricular alunos: ");
        Long id = scanner.nextLong();

        Optional<Disciplina> optional = this.disciplinaRepository.findById(id);
        if (optional.isPresent()){
            Disciplina disciplina = optional.get();
            List<Aluno> novosAlunos = this.matricular(scanner);
            disciplina.getAlunos().addAll(novosAlunos);
            this.disciplinaRepository.save(disciplina);
        }
        else {
            System.out.println("o id da disciplina informada: " + id + " é invalido!") ;
        }
    }

}

