package br.com.macedo.sistemas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.aggregate.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Integer>{

	List<Mesa> findAllByOrderByIdAsc();
	
	
}
