package br.com.alura.springdata.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

    private Boolean system = true;

    private final FuncionarioRepository repository;
    
    public RelatoriosService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Sair");
			System.out.println("1 - Busca Funcionario por nome");

            int action = scanner.nextInt();

            switch(action) {
                case 1:
                buscarfuncionarioNome(scanner);
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
}
