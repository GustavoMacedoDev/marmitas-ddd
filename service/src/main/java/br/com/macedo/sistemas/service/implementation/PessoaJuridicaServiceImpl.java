package br.com.macedo.sistemas.service.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.PessoaJuridica;
import br.com.macedo.sistemas.domain.repository.PjRepository;
import br.com.macedo.sistemas.domain.service.PessoaJuridicaService;

@Service
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService{
	
	private static final Logger log = LoggerFactory.getLogger(PessoaJuridicaServiceImpl.class);
	
	@Autowired
	private PjRepository pjRepository;

	@Override
	public List<PessoaJuridica> findAll() {
		return this.pjRepository.findAll();
	}

	@Override
	public Optional<PessoaJuridica> find(Integer id) {
		Optional<PessoaJuridica> pessoaJuridica = pjRepository.findById(id);
		return pessoaJuridica;
	}

	@Override
	public PessoaJuridica findByIdPj(Integer id) {
		PessoaJuridica pessoaJuridica = pjRepository.findByIdPj(id);
		
		return pessoaJuridica;
	}

	@Override
	public Optional<PessoaJuridica> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(pjRepository.findByCnpj(cnpj));
	}

	@Override
	public PessoaJuridica persistir(PessoaJuridica pessoaJuridica) {
		log.info("Persistindo empresa: {}", pessoaJuridica);
		return this.pjRepository.save(pessoaJuridica);
	}
	
	

}
