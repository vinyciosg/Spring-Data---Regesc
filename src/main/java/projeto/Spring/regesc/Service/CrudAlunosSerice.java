package projeto.Spring.regesc.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.stereotype.Service;
import projeto.Spring.regesc.orm.Aluno;
import projeto.Spring.regesc.repository.AlunoRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudAlunosSerice {

    private AlunoRepository alunoRepository;

    public CrudAlunosSerice(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

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
                   // this.visualizarAlunos;
                    break;
                case 4:
                    //this.deletarAluno(scanner);
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

        Aluno aluno = new Aluno(nome,idade);
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

    }


}
