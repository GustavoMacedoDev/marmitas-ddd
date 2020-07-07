package br.com.macedo.sistemas.domain.service;

import java.util.List;

import br.com.macedo.sistemas.domain.aggregate.Adicional;

public interface AdicionalService {
	
	List<Adicional> findAll();
	
	Adicional save(Adicional adicional);

}
