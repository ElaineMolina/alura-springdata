package br.com.molina.spring.data.repository;

import br.com.molina.spring.data.model.Funcionario;
import br.com.molina.spring.data.model.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario>{
    List<Funcionario> findByNome(String nome);
    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome " +
            "AND f.salario >= :salario AND f.dataContratacao = :data")
    List<Funcionario> findNomeSalarioDataContratacao(String nome, Double salario, LocalDate data);

    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data",
            nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    @Query(value= "SELECT f.id, f.nome, f.salario FROM funcionarios f",
            nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();

}
