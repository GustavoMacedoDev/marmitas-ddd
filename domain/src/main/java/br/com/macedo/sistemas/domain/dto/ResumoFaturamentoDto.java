package br.com.macedo.sistemas.domain.dto;

public class ResumoFaturamentoDto {
	
	private String pagamento;
	private Double valor;
	private Integer idFormaPagamento;
	
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getIdFormaPagamento() {
		return idFormaPagamento;
	}
	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}
	
}
