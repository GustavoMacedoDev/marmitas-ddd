package br.com.macedo.sistemas.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.Adicional;
import br.com.macedo.sistemas.domain.repository.AdicionalRepository;
import br.com.macedo.sistemas.domain.service.AdicionalService;
@Service
public class AdicionalServiceImpl implements AdicionalService {

	@Autowired
	private AdicionalRepository adicionalRepository;
	
	@Override
	public List<Adicional> findAll() {
		return this.adicionalRepository.findAll();
	}

	@Override
	public Adicional save(Adicional adicional) {
		return this.adicionalRepository.save(adicional);
	}

}
