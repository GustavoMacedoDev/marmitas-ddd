package br.com.macedo.sistemas.service.implementation;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.Cliente;
import br.com.macedo.sistemas.domain.aggregate.FormaPagamento;
import br.com.macedo.sistemas.domain.aggregate.Pagamento;
import br.com.macedo.sistemas.domain.aggregate.Pedido;
import br.com.macedo.sistemas.domain.dto.PagamentoEntregaDto;
import br.com.macedo.sistemas.domain.dto.PagamentoMesaDto;
import br.com.macedo.sistemas.domain.repository.PagamentoRepository;
import br.com.macedo.sistemas.domain.service.PagamentoService;

@Service
public class PagamentoServiceImpl implements PagamentoService{
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private FormaPagamentoServiceImpl fpService;
	
	@Autowired
	private MesaServiceImpl mesaService;
	
	@Autowired
	private ClienteServiceImpl clienteService;
	
	@Autowired
	private PedidoServiceImpl pedidoService;

	@Override
	public Pagamento insertPagamentoMesa(PagamentoMesaDto pagamentoDto) {
		String formaPagamento = pagamentoDto.getfPagamento();
		FormaPagamento fPagamento =  fpService.find(Integer.parseInt(formaPagamento));
		Cliente cliente = clienteService.findById(Integer.parseInt(pagamentoDto.getCliente()));
		
		Pagamento pagamento = new Pagamento();
		pagamento.setId(null);
		pagamento.setInstante(new Date());
		pagamento.setFormaPagamento(fPagamento);
		pagamento.setValorPago(pagamentoDto.getValorPago());
		pagamento.setMesa(pagamentoDto.getMesa());
		pagamento.setCliente(cliente);

		
		this.pagamentoRepository.save(pagamento);
		
		atualizaValorMesa(pagamento);
		
		return pagamento;
	}

	@Override
	public Pagamento insertPagamentoEntrega(PagamentoEntregaDto pagamentoDto) {
		String formaPagamento = pagamentoDto.getfPagamento();
		FormaPagamento fPagamento =  fpService.find(Integer.parseInt(formaPagamento));
		Cliente cliente = clienteService.findById(Integer.parseInt(pagamentoDto.getCliente()));
		Pedido pedido = pedidoService.find(pagamentoDto.getIdPedido());
		
		Pagamento pagamento = new Pagamento();
		pagamento.setId(null);
		pagamento.setInstante(new Date());
		pagamento.setFormaPagamento(fPagamento);
		pagamento.setValorPago(pagamentoDto.getValorPago());
		pagamento.setCliente(cliente);
		pagamento.setPedido(pedido);
		
		this.pagamentoRepository.save(pagamento);
		
		this.pedidoService.fechaPedido(pagamentoDto.getIdPedido());
		
		return pagamento;
	}

	@Override
	public void atualizaValorMesa(Pagamento pagamento) {
		this.mesaService.inserePagamentoMesa(pagamento);	
		
	}

	@Override
	public List<Pagamento> findAll() {
		return this.pagamentoRepository.findAll();
	}

	@Override
	public List<Pagamento> findByIdMesa(@Valid Integer id) {
		return this.pagamentoRepository.findByMesaId(id);
	}

	@Override
	public List<Pagamento> findByIdPedido(@Valid Integer id) {
		return this.pagamentoRepository.findByPedidoIdPedido(id);
	}

	@Override
	public void encerraPagamento(Pagamento pagamento) {
		List<Pagamento> pagamentos = this.pagamentoRepository.findByMesaId(pagamento.getMesa().getId());
		
		int status = 1;
		
		
		pagamentos.forEach(pag -> {
			pag.setStatus(status);
			this.pagamentoRepository.save(pag);
		});
	}
	
	
	
	
	
	
}
