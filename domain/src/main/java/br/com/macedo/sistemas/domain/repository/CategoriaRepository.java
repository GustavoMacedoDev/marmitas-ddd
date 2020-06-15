package br.com.macedo.sistemas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.macedo.sistemas.domain.aggregate.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	@Transactional(readOnly = true)
	Categoria findById(int id);
	
}
