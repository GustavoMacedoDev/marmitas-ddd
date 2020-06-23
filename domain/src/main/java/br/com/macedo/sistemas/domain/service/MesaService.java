package br.com.macedo.sistemas.domain.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.macedo.sistemas.domain.aggregate.Mesa;
import br.com.macedo.sistemas.domain.aggregate.Pagamento;
import br.com.macedo.sistemas.domain.aggregate.Pedido;

public interface MesaService {
	
	List<Mesa> findAllByOrderByIdAsc();
		
	Optional<Mesa> buscaMesaPorId(Integer id);

	Mesa insert(@Valid Mesa mesa);
	
	int gerCodigoAleatorio();

	void atualizaPagamentosMesa(Pedido obj);

	void inserePagamentoMesa(Pagamento pagamento);

	void encerraPagamentos(Pagamento pagamento);
	
	void fechaPedidos(Mesa mesa);

}
