package br.com.macedo.sistemas.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.Cliente;
import br.com.macedo.sistemas.domain.aggregate.Endereco;
import br.com.macedo.sistemas.domain.dto.ClienteNewDto;
import br.com.macedo.sistemas.domain.repository.ClienteRepository;
import br.com.macedo.sistemas.domain.repository.EnderecoRepository;
import br.com.macedo.sistemas.domain.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public Cliente save(Cliente cliente) {
		
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	@Override
	public Cliente findById(Integer id) {
		return clienteRepository.getOne(id);
	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente findByTelefone(Long telefone) {
		return clienteRepository.findByTelefone(telefone);
	}

	@Override
	public Cliente fromDTO(ClienteNewDto clienteNewDto) {
		Cliente cliente = new Cliente(null, clienteNewDto.getNome(), clienteNewDto.getTelefone());
		Endereco endereco = new Endereco(null, clienteNewDto.getLogradouro(), clienteNewDto.getNumero(), clienteNewDto.getComplemento(),
				clienteNewDto.getBairro(), cliente);
		cliente.getEnderecos().add(endereco);

		return cliente;
	}
	
	
	
}
