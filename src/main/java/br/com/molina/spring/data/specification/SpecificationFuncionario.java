package br.com.molina.spring.data.specification;

import br.com.molina.spring.data.model.Funcionario;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationFuncionario {

    public static Specification<Funcionario> nome(String nome){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
    public static Specification<Funcionario> cpf(String cpf){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cpf"), cpf);
    }
    public static Specification<Funcionario> salario(Double salario){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("salario"), salario);
    }
    public static Specification<Funcionario> dataContratacao(String dataContratacao){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao);
    }
}
