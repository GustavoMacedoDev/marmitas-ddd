package br.com.macedo.sistemas.domain.service;

import java.util.List;

import br.com.macedo.sistemas.domain.aggregate.Cliente;
import br.com.macedo.sistemas.domain.dto.ClienteNewDto;

public interface ClienteService {
	
	Cliente save(Cliente obj);
	
	Cliente findById(Integer id);
		
	List<Cliente> findAll();
	
	Cliente findByTelefone(Long telefone);

	Cliente fromDTO(ClienteNewDto clienteNewDto);

}
