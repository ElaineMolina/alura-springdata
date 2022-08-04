package br.com.molina.spring.data.service;

import br.com.molina.spring.data.model.Funcionario;
import br.com.molina.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;

    }
    public void inicial(Scanner scanner){

        while(system){
            System.out.println("Qual acao de cargo deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionário pelo nome");
            System.out.println("2 - Buscar funcionário pelo nome, data contratação e salário maior");
            System.out.println("3 - Buscar funcionário pela data de contratação");


            int action = scanner.nextInt();

            switch (action){
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionariNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;


                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner){
        System.out.println("Qual Funcionário deseja pesquisar?");
        String nome = scanner.next();
        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);
    }

    private void buscaFuncionariNomeSalarioMaiorData(Scanner scanner){
        System.out.println("Qual Funcionário deseja pesquisar?");
        String nome = scanner.next();

        System.out.println("Qual data de contratação deseja pesquisar?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Qual salário  deseja pesquisar?");
        Double salario = scanner.nextDouble();

        List<Funcionario> list = funcionarioRepository
                .findNomeSalarioDataContratacao(nome, salario, localDate);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner){
        System.out.println("Qual data de contratação deseja pesquisar?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
        list.forEach(System.out::println);

    }
}
