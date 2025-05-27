package projeto.Spring.regesc.Service;

import org.springframework.stereotype.Service;
import projeto.Spring.regesc.Professor;
import projeto.Spring.regesc.repository.professorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudProfessorService {

    // dependencia da classe CrudProfessorService
    private professorRepository professorRepository;

    // o Spring automaticamente cria um objeto com a interface professorRepository
    // e injeta para nós no construtor na classe atual ==> Injeção de independência
    public CrudProfessorService(professorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner){
        Boolean istrue = true;

        while (istrue){
            System.out.println("\nQual ação voce quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar um novo professor");
            System.out.println("2 - Atualizar um professor");
            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    this.cadastrarProfessor(scanner);
                    System.out.println("Professor cadastrado com sucesso");
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                default:
                    istrue = false;
                        break;
            }
        }
    }
    private void cadastrarProfessor(Scanner scanner){
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.next(); // lê a próxima String até achar um enter ou um espaco

        System.out.print("Digite o prontuario do professor: ");
        String prontuario = scanner.next();

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor salvo no banco!!\n ");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Digite o ID do professor a ser atualizado: ");
        Long id = scanner.nextLong();

        Optional<Professor> optional = this.professorRepository.findById(id);

        if (optional.isPresent()){


            System.out.print("Digite o nome do professor: ");
            String nome = scanner.next(); // lê a próxima String até achar um enter ou um espaco

            System.out.print("Digite o prontuario do professor: ");
            String prontuario = scanner.next();

            Professor professor = optional.get();
            professor.setNome(nome);
            professor.setProntuario(prontuario);

            professorRepository.save(professor);// atualiza (perciste) o objeto/ registro/ tabela no BD
        }
        else {
        System.out.println("O id do professor informado " + id + " é invalido!!");
        }
    }

}
