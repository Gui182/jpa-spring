package br.com.alura.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;

@Service
public class CrudCargoService {
    
    private Boolean system = true;
    private final CargoRepository repository;

    public CrudCargoService(CargoRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch(action) {
                case 1:
                salvar(scanner);
                break;

                case 2:
                atualizar(scanner);
                break;

                case 3:
                visualizar();
                break;

                case 4:
                deletar(scanner);
                break;

                default:
                system = false;
                break;
            }
        }
    }

    private void salvar(Scanner scanner) {
        System.out.println("Descrição do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        repository.save(cargo);
        System.out.println("Salvo!!");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        System.out.println("Insira a nova descrição do Cargo");
        String descricao = scanner.next();
       
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        repository.save(cargo);
        System.out.println("Atualizado!!!");
    }

    private void visualizar() {
        Iterable<Cargo> cargos = repository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        repository.deleteById(id);
        System.out.println("Deletado!!!");
    }
}
