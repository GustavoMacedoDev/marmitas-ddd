package br.com.macedo.sistemas.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.macedo.sistemas.domain.aggregate.Mesa;
import br.com.macedo.sistemas.domain.aggregate.Pedido;
import br.com.macedo.sistemas.domain.dto.ListaPedidoEntregaDto;
import br.com.macedo.sistemas.domain.dto.PedidoNewDto;

public interface PedidoService {
	
	List<Pedido> findAll();
	
	ListaPedidoEntregaDto findById(Integer id);

	
	Pedido insertEntrega(PedidoNewDto obj);
	
	Pedido insertMesa(Pedido obj);
	
	Pedido insertBalcao(Pedido obj);

	void atualizaMesa(Pedido obj);

	List<Pedido> findByOpAtendimentoIdDataAtual(Integer id);

	List<Pedido> findByMesaId(Integer id);
	
	void fechaPedidosMesa(Mesa mesa);
	
	void fechaPedidosEntrega(Pedido pedido);

	Optional<Pedido> findByPedidoId(Integer id);

	void fechaPedido(Integer id);

	List<Pedido> findByOpAtendimentoIdInativos(Integer id); 

	Pedido find(Integer idPedido);
	
	int totalDePedidos();

}
