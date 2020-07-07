package br.com.macedo.sistemas.service.implementation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.ItemPedido;
import br.com.macedo.sistemas.domain.aggregate.Mesa;
import br.com.macedo.sistemas.domain.aggregate.OpcaoAtendimento;
import br.com.macedo.sistemas.domain.aggregate.Pedido;
import br.com.macedo.sistemas.domain.dto.ListaPedidoEntregaDto;
import br.com.macedo.sistemas.domain.dto.PedidoNewDto;
import br.com.macedo.sistemas.domain.exceptions.ObjectNotFoundException;
import br.com.macedo.sistemas.domain.repository.ItemPedidoRepository;
import br.com.macedo.sistemas.domain.repository.PedidoRepository;
import br.com.macedo.sistemas.domain.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private MesaServiceImpl mesaService;

	@Override
	public List<Pedido> findAll() {
		return this.pedidoRepository.findAll();	
	}

	@Override
	public ListaPedidoEntregaDto findById(Integer id) {
		Pedido pedido = this.pedidoRepository.getOne(id);

		ListaPedidoEntregaDto listaPedido = new ListaPedidoEntregaDto();
		listaPedido.setIdPedido(pedido.getIdPedido());
		listaPedido.setInstante(pedido.getInstante());
		listaPedido.setCliente(pedido.getCliente());
		listaPedido.setItens(pedido.getItens());
		listaPedido.setFormaPagamento(pedido.getFormaPagamento());
		listaPedido.setTotalPedido(pedido.getTotalPedido());
		listaPedido.setValorPago(pedido.getValorPago());
		listaPedido.setObservacao(pedido.getObservacao());
		
		return listaPedido;		
	}

	@Override
	public Pedido insertEntrega(PedidoNewDto obj) {
		OpcaoAtendimento op = new OpcaoAtendimento();
		op.setId(2);
		Pedido pedido = new Pedido();
		
		if(obj.getValorPago() != null)  {
			System.out.println("oi meu chaapa");
			pedido.setValorPago(obj.getValorPago());
		} else {
			pedido.setValorPago(obj.getTotalPedido());
		}
		
		
		pedido.setInstante(new Date());
		pedido.setCliente(obj.getCliente());
		pedido.setFormaPagamento(obj.getFormaPagamento());
		pedido.setOpAtendimento(op);
		pedido.setTotalPedido(obj.getTotalPedido());
				
				
		pedido.setObservacao(obj.getObservacao());

		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
			
		}
		
		pedido = pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(obj.getItens());
		
		return pedido;
	}

	@Override
	public Pedido insertMesa(Pedido obj) {
		obj.setIdPedido(null);
		obj.setInstante(new Date());
		obj.setFormaPagamento(null);
		obj.setCliente(null);
		obj.setOpAtendimento(obj.getOpAtendimento());
		
		double soma = 0;
		
		for (ItemPedido ip : obj.getItens()) {
			
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			double preco = ip.getProduto().getPreco();
			int quantidade = ip.getQuantidade();
			double total = preco * quantidade;
			soma += total;
			ip.setPedido(obj);
		}
		
		obj.setTotalPedido(soma);
		obj = pedidoRepository.save(obj);
		itemPedidoRepository.saveAll(obj.getItens());
		
		atualizaMesa(obj);
		return obj;
	}

	@Override
	public Pedido insertBalcao(Pedido obj) {
		obj.setIdPedido(null);
		obj.setInstante(new Date());
		obj.setFormaPagamento(null);
		obj.setCliente(null);
		obj.setOpAtendimento(obj.getOpAtendimento());
		
		double soma = 0;
		
		for (ItemPedido ip : obj.getItens()) {
			
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			double preco = ip.getProduto().getPreco();
			int quantidade = ip.getQuantidade();
			double total = preco * quantidade;
			soma += total;
			ip.setPedido(obj);
		}
		
		obj.setTotalPedido(soma);
		obj = pedidoRepository.save(obj);
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
	}

	@Override
	public void atualizaMesa(Pedido obj) {
		mesaService.atualizaPagamentosMesa(obj);
		
	}

	

	@Override
	public List<Pedido> findByMesaId(Integer id) {
		return this.pedidoRepository.findByMesaId(id);
	}

	@Override
	public void fechaPedidosMesa(Mesa mesa) {
		this.pedidoRepository.fechaPedidosMesa(mesa.getId());	
		
	}

	@Override
	public void fechaPedidosEntrega(Pedido pedido) {
		this.pedidoRepository.fechaPedidosMesa(pedido.getIdPedido());
		
	}

	@Override
	public Optional<Pedido> findByPedidoId(Integer id) {
		return this.pedidoRepository.findById(id);
	}

	@Override
	public void fechaPedido(Integer id) {
		this.pedidoRepository.fechaPedidosEntrega(id);
		
	}

	@Override
	public List<Pedido> findByOpAtendimentoIdInativos(Integer id) {
		return this.pedidoRepository.findByOpAtendimentoIdInativos(id);
	}

	@Override
	public Pedido find(Integer idPedido) {
		Optional<Pedido> obj = pedidoRepository.findById(idPedido);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + idPedido + ", Tipo: " + Pedido.class.getName()));
	}

	@Override
	public List<Pedido> findByOpAtendimentoIdDataAtual(Integer id) {
		return this.pedidoRepository.findByOpAtendimentoId(id);
	}

	@Override
	public int totalDePedidos() {
		return this.pedidoRepository.totaldePedidos();
	}
	
	
	
}
