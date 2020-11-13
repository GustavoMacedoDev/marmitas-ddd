package br.com.macedo.sistemas.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.TaxaEntrega;
import br.com.macedo.sistemas.domain.dto.TaxaEntregaDto;
import br.com.macedo.sistemas.domain.repository.TaxaEntregaRepository;
import br.com.macedo.sistemas.domain.service.TaxaEntregaService;

@Service
public class TaxaEntregaServiceImpl implements TaxaEntregaService {
	
	@Autowired
	private TaxaEntregaRepository txRepository;

	@Override
	public List<TaxaEntrega> findAll() {
		return this.txRepository.findAll();
	}

	@Override
	public TaxaEntrega insert(TaxaEntregaDto txEntregaDto) {
		TaxaEntrega taxaEntrega = new TaxaEntrega();
		taxaEntrega.setId(null);
		taxaEntrega.setDescricao(txEntregaDto.getDescricao());
		taxaEntrega.setValor(txEntregaDto.getValor());
		
		txRepository.save(taxaEntrega);
		
		return taxaEntrega;
	}

	
	
}
