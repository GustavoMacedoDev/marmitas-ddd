package br.com.macedo.sistemas.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.Mesa;
import br.com.macedo.sistemas.domain.aggregate.Pagamento;
import br.com.macedo.sistemas.domain.aggregate.Pedido;
import br.com.macedo.sistemas.domain.repository.MesaRepository;
import br.com.macedo.sistemas.domain.service.MesaService;

@Service
public class MesaServiceImpl implements MesaService {

	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
	private PedidoServiceImpl pedidoService;
	
	@Autowired
	private PagamentoServiceImpl pagamentoService;

	@Override
	public List<Mesa> findAllByOrderByIdAsc() {
		return this.mesaRepository.findAllByOrderByIdAsc();
	}

	@Override
	public Optional<Mesa> buscaMesaPorId(Integer id) {
		return this.mesaRepository.findById(id);
	}

	@Override
	public Mesa insert(@Valid Mesa mesaObj) {
		Mesa mesa = new Mesa();
		mesa.setId(null);
		mesa.setNumeroMesa(mesaObj.getNumeroMesa());
		mesa.setCodigoMesa(gerCodigoAleatorio());
		
		mesaRepository.save(mesa);
		
		return mesa;
	}

	@Override
	public int gerCodigoAleatorio() {
		Random random = new Random();
		int codigoAleatorio = random.nextInt(100000);
		return codigoAleatorio;
	}

	@Override
	public void atualizaPagamentosMesa(Pedido obj) {
		Mesa mesa = obj.getMesa();
		mesa = mesaRepository.getOne(mesa.getId());
		
		double totalMesa = mesa.getTotalMesa();
		double novoValor = 0;
		
		double totalAtual = obj.getTotalPedido();
		
		novoValor = totalMesa + totalAtual;
		
		mesa.setTotalMesa(novoValor);
		
		mesaRepository.save(mesa);
		
	}

	@Override
	public void inserePagamentoMesa(Pagamento pagamento) {
		Mesa mesa = pagamento.getMesa();
		mesa = mesaRepository.getOne(mesa.getId());
		
		double totalParcial = mesa.getValorPagoParcial();
		double novoValor = 0;
		
		double valorPagoParcial = pagamento.getValorPago();
		
		novoValor = totalParcial + valorPagoParcial;
		
		double totalMesa = mesa.getTotalMesa();
		
		if(totalMesa == novoValor) {
			fechaPedidos(mesa);
			mesa.setValorPagoParcial(0);
			mesa.setTotalMesa(0);
			novoValor = 0;
			encerraPagamentos(pagamento);
		}
		
		mesa.setValorPagoParcial(novoValor);
		
		mesaRepository.save(mesa);
		
	}

	@Override
	public void encerraPagamentos(Pagamento pagamento) {
		this.pagamentoService.encerraPagamento(pagamento);
		
	}

	@Override
	public void fechaPedidos(Mesa mesa) {
		this.pedidoService.fechaPedidosMesa(mesa);
		
	}
	
	
}
