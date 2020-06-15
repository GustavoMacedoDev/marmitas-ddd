package br.com.macedo.sistemas.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.Endereco;
import br.com.macedo.sistemas.domain.repository.EnderecoRepository;
import br.com.macedo.sistemas.domain.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService{
	
	@Autowired
	private EnderecoRepository endRepository;

	@Override
	public Endereco insert(Endereco endereco) {
		return endRepository.save(endereco);
	}
	
	

}
