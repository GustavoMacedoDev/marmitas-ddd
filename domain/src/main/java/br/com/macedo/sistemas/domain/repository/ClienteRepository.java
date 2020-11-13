package br.com.macedo.sistemas.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.macedo.sistemas.domain.aggregate.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Transactional(readOnly=true)
	Cliente findByTelefone(Long telefone);
	
	List<Cliente> findAllByOrderByIdAsc();
	

}
