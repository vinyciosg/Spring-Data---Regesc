package projeto.Spring.regesc.Service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import projeto.Spring.regesc.orm.Disciplina;
import projeto.Spring.regesc.orm.Professor;
import projeto.Spring.regesc.repository.ProfessorRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudProfessorService {

    // dependencia da classe CrudProfessorService
    private ProfessorRepository professorRepository;

    // o Spring automaticamente cria um objeto com a interface professorRepository
    // e injeta para nós no construtor na classe atual ==> Injeção de independência
    public CrudProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    @Transactional
    public void menu(Scanner scanner){
        Boolean istrue = true;
        while (istrue){
            System.out.println("\nQual ação voce quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar um novo professor");
            System.out.println("2 - Atualizar um professor");
            System.out.println("3 - Visualizar todos os professores");
            System.out.println("4 - Deletar um professores");
            System.out.println("5 - Vizualizar um professor");
            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    this.cadastrarProfessor(scanner);
                    System.out.println("Professor cadastrado com sucesso");
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.visualizar();
                    break;
                case 4:
                    this.deletar(scanner);
                    break;
                case 5:
                    this.visualizarProfessor(scanner);
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
        professor.setNome(nome);
        professor.setProntuario(prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor salvo no banco!!\n ");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Digite o ID do professor a ser atualizado: ");
        Long id = scanner.nextLong();

        // Classe container para lidar com valores que podem ser nulos // verifica se existe algo la dentro = Professor
        Optional<Professor> optional = this.professorRepository.findById(id); // findByID Metodo de busca de entidade pelo seu ID

        if (optional.isPresent()){ // optional.isPresente() verifica se o Optional contém um valor nao nulo

            System.out.print("Digite o nome do professor: ");
            String nome = scanner.next(); // lê a próxima String até achar um enter ou um espaco

            System.out.print("Digite o prontuario do professor: ");
            String prontuario = scanner.next();

            Professor professor = optional.get(); // optional.get() Recupera o objeto professor contido no Optional // atualiza os atributos
            professor.setNome(nome);
            professor.setProntuario(prontuario);

            professorRepository.save(professor);// atualiza (perciste) o objeto/ registro/ tabela no BD
        }
        else {
             System.out.println("O id do professor informado " + id + " é invalido!!");
        }
    }

    private void visualizar(){

        Iterable<Professor> professores = this.professorRepository.findAll(); // findAll retorna todos os registros da tabela professor
        // Alternativa 1
        for(Professor professor : professores){
            System.out.println(professor);
        }

        // Alternativa 2
        /*professores.forEach(professor -> { // lambda(função anonima) executa uma ação para cada elemento da coleção
            System.out.println(professor);
        });

        Alternativa 3
        professores.forEach(System.out::println);*/

        System.out.println();
    }

    private void deletar(Scanner scanner){
        System.out.println("Digite o ID do professor pra deletar: ");
        Long id = scanner.nextLong();
        this.professorRepository.deleteById(id); // lancará um exception se não achar o ID passado na tabela
        System.out.println( "Professor deletado!\n");
    }

    @Transactional // faz uma transação com o banco de Dados
    private void visualizarProfessor(Scanner scanner){
        System.out.print("ID do professor: ");
        Long id = scanner.nextLong();

        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()){
            Professor professor = optional.get();

            System.out.println("Professor [");
            System.out.println("ID: " + professor.getId());
            System.out.println("Nome: " + professor.getNome());
            System.out.println("Prontuario: " + professor.getProntuario());
            System.out.println();

            System.out.println("Disciplinas [");
            for (Disciplina disciplina : professor.getDisciplinas()){
                System.out.println("ID: " + disciplina.getId());
                System.out.println("Nome: " + disciplina.getNome());
                System.out.println("Semestr: " + disciplina.getSemestre());
                System.out.println();
            }
            System.out.println("]\n}");
        }
    }
}
