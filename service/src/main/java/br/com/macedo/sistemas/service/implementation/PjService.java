package br.com.macedo.sistemas.service.implementation;

import java.util.Optional;

import br.com.macedo.sistemas.domain.aggregate.PessoaJuridica;

public interface PjService {
	
	Optional<PessoaJuridica> buscarPorCnpj(String cnpj);
	
	PessoaJuridica persistir(PessoaJuridica pessoaJuridica);

}
