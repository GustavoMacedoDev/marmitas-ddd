package br.com.macedo.sistemas.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.OpcaoAtendimento;
import br.com.macedo.sistemas.domain.repository.OpcaoAtendimentoRepository;
import br.com.macedo.sistemas.domain.service.OpcaoAtendimentoService;

@Service
public class OpcaoAtendimentoServiceImpl implements OpcaoAtendimentoService{
	
	@Autowired
	private OpcaoAtendimentoRepository opRepository;

	@Override
	public List<OpcaoAtendimento> findAll() {
		return opRepository.findAll();
	}
	
	
}
