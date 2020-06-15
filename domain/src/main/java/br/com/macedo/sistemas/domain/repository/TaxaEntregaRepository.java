package br.com.macedo.sistemas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.aggregate.TaxaEntrega;

public interface TaxaEntregaRepository extends JpaRepository<TaxaEntrega, Integer>{
	

}
