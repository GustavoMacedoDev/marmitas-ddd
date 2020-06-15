package br.com.macedo.sistemas.domain.service;

import java.util.List;

import br.com.macedo.sistemas.domain.aggregate.OpcaoAtendimento;

public interface OpcaoAtendimentoService {
	
	List<OpcaoAtendimento> findAll();


}
