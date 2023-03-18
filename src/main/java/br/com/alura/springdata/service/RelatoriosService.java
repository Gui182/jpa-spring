package br.com.alura.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.FuncionarioProjecao;
import br.com.alura.springdata.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

    private Boolean system = true;

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository repository;

    public RelatoriosService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca Funcionario por nome");
            System.out.println("2 - Busca Funcionario por nome e salario");
            System.out.println("3 - Busca Funcionario por data de contratacao");
            System.out.println("4 - Pesquisa Funcionario salario");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscarfuncionarioNome(scanner);
                    break;

                    case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;

                    case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;

                    case 4:
                    pesquisaFuncionarioSalario();
                    break;

                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscarfuncionarioNome(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar?");
        String nome = scanner.next();
        List<Funcionario> list = repository.findByNome(nome);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar?");
        String nome = scanner.next();

        System.out.println("Qual data contratação deseja pesquisar?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, format);

        System.out.println("Qual salario deseja pesquisar?");
        Double salario = scanner.nextDouble();

        List<Funcionario> list = repository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner) {
        System.out.println("Qual data contratação deseja pesquisar?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, format);

        List<Funcionario> list = repository.findDataContratacaoMaior(localDate);
        list.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario() {
        List<FuncionarioProjecao> list = repository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionario: id: " + f.getId()
            + " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
    }

}
