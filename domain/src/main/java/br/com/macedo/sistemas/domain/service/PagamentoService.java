package br.com.macedo.sistemas.domain.service;

import java.util.List;

import javax.validation.Valid;

import br.com.macedo.sistemas.domain.aggregate.Pagamento;
import br.com.macedo.sistemas.domain.dto.PagamentoEntregaDto;
import br.com.macedo.sistemas.domain.dto.PagamentoMesaDto;
import br.com.macedo.sistemas.domain.dto.ResumoFaturamentoDto;

public interface PagamentoService {
	
	Pagamento insertPagamentoMesa(PagamentoMesaDto pagamentoDto);

	Pagamento insertPagamentoEntrega(PagamentoEntregaDto pagamentoDto);

	void atualizaValorMesa(Pagamento pagamento);

	List<Pagamento> findAll();

	List<Pagamento> findByIdMesa(@Valid Integer id); 
	
	List<Pagamento> findByIdPedido(@Valid Integer id);

	void encerraPagamento(Pagamento pagamento);
	
	List<ResumoFaturamentoDto> resumoFaturamento();
	
	List<Pagamento> pagamentosPorFormaPagamento(Integer id);
	

}
