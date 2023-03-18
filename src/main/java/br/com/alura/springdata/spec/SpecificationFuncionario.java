package br.com.alura.springdata.spec;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.springdata.orm.Funcionario;

public class SpecificationFuncionario {
    
    public static Specification<Funcionario> nome(String nome) {
        return (root, creteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }

    public static Specification<Funcionario> cpf(String cpf) {
        return (root, creteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.equal(root.get("cpf"), cpf);
    }
    
    public static Specification<Funcionario> salario(Double salario) {
        return (root, creteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.greaterThan(root.get("salario"), salario);
    }

    public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
        return (root, creteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao);
    }

}
