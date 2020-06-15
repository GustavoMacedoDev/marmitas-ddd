package br.com.macedo.sistemas.domain.service;

import java.util.List;

import br.com.macedo.sistemas.domain.aggregate.TaxaEntrega;
import br.com.macedo.sistemas.domain.dto.TaxaEntregaDto;

public interface TaxaEntregaService {
	
	List<TaxaEntrega> findAll();;
	
	TaxaEntrega insert(TaxaEntregaDto txEntregaDto);

}
