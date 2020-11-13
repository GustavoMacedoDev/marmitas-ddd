package br.com.macedo.sistemas.domain.service;

import java.util.List;

import br.com.macedo.sistemas.domain.aggregate.Pagamento;

public interface FaturamentoService {
	
	List<Pagamento> findByPagamentoId(Integer id);

}
