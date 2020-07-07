package br.com.macedo.sistemas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.aggregate.Adicional;

public interface AdicionalRepository extends JpaRepository<Adicional, Long>{

}
