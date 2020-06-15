package br.com.macedo.sistemas.domain.service;

import java.util.List;

import br.com.macedo.sistemas.domain.aggregate.FormaPagamento;

public interface FormaPagamentoService {
	
	List<FormaPagamento> findAll();
	
	FormaPagamento find(Integer id);

}
