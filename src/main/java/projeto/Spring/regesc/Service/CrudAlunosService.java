package projeto.Spring.regesc.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projeto.Spring.regesc.orm.Aluno;
import projeto.Spring.regesc.orm.Disciplina;
import projeto.Spring.regesc.repository.AlunoRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudAlunosService {
    private AlunoRepository alunoRepository;

    public CrudAlunosService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void menu(Scanner scanner){
        Boolean istrue = true;
        while (istrue){

            System.out.println("\nQual ação voce quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar um novo aluno");
            System.out.println("2 - Atualizar um aluno");
            System.out.println("3 - Visualizar todos os alunos");
            System.out.println("4 - Deletar um aluno");
            System.out.println("5 - Vizualizar um aluno");
            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    this.cadastroAluno(scanner);
                    break;
                case 2:
                    this.atualizarAluno(scanner);
                    break;
                case 3:
                    this.visualizarAlunos();
                    break;
                case 4:
                    this.deletarAluno(scanner);
                    break;
                case 5:
                    this.visualizarUmAluno(scanner);
                default:
                    istrue = false;
                    break;
            }
        }
    }

    private void cadastroAluno(Scanner scanner){
        System.out.print("Nome: ");
        String nome = scanner.next();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        this.alunoRepository.save(aluno);
        System.out.println("Aluno salvo!!");
    }

    private void  atualizarAluno(Scanner scanner){
        System.out.print("Digite o ID do aluno: ");
        Long id = scanner.nextLong();

        Optional<Aluno> optional = this.alunoRepository.findById(id);
        if (optional.isPresent()){
            System.out.print("Nome: ");
            String nome = scanner.next();

            System.out.print("Idade:");
            int idade = scanner.nextInt();

            Aluno aluno =  optional.get();
            aluno.setNome(nome);
            aluno.setIdade(idade);
            this.alunoRepository.save(aluno);
        }
        else {
            System.out.println("ID do aluno informado" + id + "invalido!!");
        }
    }

    private void visualizarAlunos(){
        Iterable<Aluno> alunos = this.alunoRepository.findAll();
        for (Aluno aluno : alunos){
            System.out.println(aluno);
        }
    }

    private void deletarAluno(Scanner scanner){
        System.out.print("ID do aluno para deletar: ");
        Long id = scanner.nextLong();
        this.alunoRepository.deleteById(id);
        System.out.println("Aluno deletado!");
    }

    @Transactional
    private void visualizarUmAluno(Scanner scanner){
        System.out.print("ID do aluno: ");
        Long id = scanner.nextLong();

        Optional<Aluno> optional = this.alunoRepository.findById(id);
        if (optional.isPresent()){
            Aluno aluno = optional.get();
            System.out.println("ID: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Idade: " + aluno.getIdade());

            if (aluno.getDisciplinas() != null) {
                for (Disciplina disciplina : aluno.getDisciplinas()){
                    System.out.println("\t- Disciplina: " + disciplina.getNome());
                    System.out.println("\t- Semestre: " + disciplina.getSemestre());
                    System.out.println();
                }
            }
        }
        else {
            System.out.println("ID invalido!!");
        }
    }

}
