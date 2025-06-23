package projeto.Spring.regesc.Service;

import org.springframework.stereotype.Service;
import projeto.Spring.regesc.orm.Aluno;
import projeto.Spring.regesc.repository.AlunoRepository;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioServiceGeral {
    private AlunoRepository alunoRepository;

    public RelatorioServiceGeral(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void menu(Scanner scanner){
        Boolean istrue = true;
        while (istrue){

            System.out.println("\nQual relatorio voce deseja?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Alunos por um dado Nome");
            System.out.println("2 - Alunos por um dado Nome e Idade menor ou igual");

            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    this.AlunosPorNome(scanner);
                    break;
                case 2:
                    this.AlunosPorNomeIdadeMenorOuIgual(scanner);
                default:
                    istrue = false;
                    break;
            }
        }
    }

    private void AlunosPorNome(Scanner scanner){
        System.out.println("Nome: ");
        String nome = scanner.next();

        List<Aluno> alunosl = this.alunoRepository.findByNomeStartingWith(nome);

        for (Aluno aluno : alunosl){
            System.out.println(aluno);
        }
    }

    private void AlunosPorNomeIdadeMenorOuIgual(Scanner scanner){
        System.out.println("Nome: ");
        String nome = scanner.next();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();

        List<Aluno> alunosl = this.alunoRepository.findByNomeStartingWithAndIdadeLessThanEqual(nome, idade);
        for (Aluno aluno : alunosl){
            System.out.println(aluno);
        }
    }

}
