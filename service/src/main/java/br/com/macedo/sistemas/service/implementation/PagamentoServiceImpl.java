package br.com.macedo.sistemas.service.implementation;

import java.util.ArrayList;
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
import br.com.macedo.sistemas.domain.dto.ResumoFaturamentoDto;
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

	@Override
	public List<ResumoFaturamentoDto> resumoFaturamento() {
		List<Pagamento> pagamentos = new ArrayList<>();
		
		pagamentos = pagamentoRepository.findAll();
		
		ResumoFaturamentoDto dinheiro = new ResumoFaturamentoDto();
		ResumoFaturamentoDto debito = new ResumoFaturamentoDto();
		ResumoFaturamentoDto credito = new ResumoFaturamentoDto();
		ResumoFaturamentoDto alimentacao = new ResumoFaturamentoDto();
		ResumoFaturamentoDto assinar = new ResumoFaturamentoDto();
		ResumoFaturamentoDto online = new ResumoFaturamentoDto();
		
		double somaDinheiro = 0;
		double somaDebito = 0;
		double somaCredito = 0;
		double somaAlimentacao = 0;
		double somaAssinar = 0;
		double somaOnline = 0;
		for(Pagamento pags: pagamentos) {
			if(pags.getFormaPagamento().getId() == 1 ) {
					dinheiro.setIdFormaPagamento(pags.getFormaPagamento().getId());
					dinheiro.setPagamento(pags.getFormaPagamento().getFormaPagamento());
					somaDinheiro += pags.getValorPago();
					dinheiro.setValor(somaDinheiro);
			} else if(pags.getFormaPagamento().getId() == 2 ) {
					debito.setIdFormaPagamento(pags.getFormaPagamento().getId());
					debito.setPagamento(pags.getFormaPagamento().getFormaPagamento());
					somaDebito += pags.getValorPago();
					debito.setValor(somaDebito);
			} else if(pags.getFormaPagamento().getId() == 3 ) {
					credito.setIdFormaPagamento(pags.getFormaPagamento().getId());
					credito.setPagamento(pags.getFormaPagamento().getFormaPagamento());
					somaCredito += pags.getValorPago();
					credito.setValor(somaCredito);
			} else if(pags.getFormaPagamento().getId() == 4 ) {
					String alimentacaoPag = pags.getFormaPagamento().getFormaPagamento();
					System.out.println(alimentacaoPag);
					if(alimentacaoPag == null) {
						alimentacao.setPagamento("Alimentação");
						alimentacao.setValor(0.0);
					} else {
						alimentacao.setIdFormaPagamento(pags.getFormaPagamento().getId());
						somaAlimentacao += pags.getValorPago();
						alimentacao.setValor(somaAlimentacao);
					}
					
			} else if(pags.getFormaPagamento().getId() == 5 ) {
					assinar.setIdFormaPagamento(pags.getFormaPagamento().getId());
					assinar.setPagamento(pags.getFormaPagamento().getFormaPagamento());
					somaAssinar += pags.getValorPago();
					assinar.setValor(somaAssinar);
			} else if(pags.getFormaPagamento().getId() == 6 ) {
					String onlinePag = pags.getFormaPagamento().getFormaPagamento();
					
					System.out.println(onlinePag);
					if(onlinePag == null) {
						online.setPagamento("Pagamento Online");
						online.setValor(0.0);
					} else {
						online.setIdFormaPagamento(pags.getFormaPagamento().getId());
						somaOnline += pags.getValorPago();
						online.setValor(somaOnline);
					}
					
			}
				
			
		}
		
		List<ResumoFaturamentoDto> resumos = new ArrayList<>();
		resumos.add(dinheiro);
		resumos.add(debito);
		resumos.add(credito);
		if(alimentacao.getPagamento() != null) {
			resumos.add(alimentacao);
		}
		
		resumos.add(assinar);
		if(online.getPagamento() != null) {
			resumos.add(online);
		}
		
		return resumos;
	
	}
	 @Override
	public List<Pagamento> pagamentosPorFormaPagamento(Integer id) {
		return this.pagamentoRepository.findByFormaPagamentoId(id);
	}
}
