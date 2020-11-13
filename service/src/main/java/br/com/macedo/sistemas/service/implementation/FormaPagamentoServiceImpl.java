package br.com.macedo.sistemas.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.Cliente;
import br.com.macedo.sistemas.domain.aggregate.FormaPagamento;
import br.com.macedo.sistemas.domain.exceptions.ObjectNotFoundException;
import br.com.macedo.sistemas.domain.repository.FormaPagamentoRepository;
import br.com.macedo.sistemas.domain.service.FormaPagamentoService;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Override
	public List<FormaPagamento> findAll() {

		return formaPagamentoRepository.findAll();
	}

	@Override
	public FormaPagamento find(Integer id) {

		Optional<FormaPagamento> obj = formaPagamentoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	
	
}
