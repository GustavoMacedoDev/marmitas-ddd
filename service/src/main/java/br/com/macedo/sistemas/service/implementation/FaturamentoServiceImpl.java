package br.com.macedo.sistemas.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.macedo.sistemas.domain.aggregate.Pagamento;
import br.com.macedo.sistemas.domain.repository.PagamentoRepository;
import br.com.macedo.sistemas.domain.service.FaturamentoService;

public class FaturamentoServiceImpl implements FaturamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Override
	public List<Pagamento> findByPagamentoId(Integer id) {
		
		return this.pagamentoRepository.findByFormaPagamentoId(id);
		
	}

}
