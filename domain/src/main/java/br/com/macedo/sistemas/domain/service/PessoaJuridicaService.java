package br.com.macedo.sistemas.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.macedo.sistemas.domain.aggregate.PessoaJuridica;

public interface PessoaJuridicaService {
	
	List<PessoaJuridica> findAll();
	
	Optional<PessoaJuridica> find(Integer id);
	
	PessoaJuridica findByIdPj(Integer id);
	
	Optional<PessoaJuridica> buscarPorCnpj(String cnpj);
	
	PessoaJuridica persistir(PessoaJuridica pessoaJuridica);

}
