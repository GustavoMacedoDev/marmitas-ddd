package br.com.macedo.sistemas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.aggregate.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	List<Produto> findAllByStatus(int status);

}
