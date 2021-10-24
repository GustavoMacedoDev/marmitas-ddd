package br.com.macedo.sistemas.domain.aggregate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Getter @Setter @EqualsAndHashCode
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer idPedido;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	@ManyToOne
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "mesa_id")
	private Mesa mesa;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();
	
	@Column(name = "total_pedido")
	private Double totalPedido;
	
	@Column(name = "valor_pago")
	private Double valorPago;
	
	@ManyToOne
	private OpcaoAtendimento opAtendimento;
	
	@Column(name = "status")
	private int status = 0;
	
	@Column(name = "observacao")
	private String observacao;
	
	@OneToMany(mappedBy = "pedido")
	@JsonIgnore
	private List<Pagamento> pagamentos = new ArrayList<>();
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}
	

	public Pedido(Integer idPedido, Date instante, FormaPagamento formaPagamento, Cliente cliente) {
		super();
		this.idPedido = idPedido;
		this.instante = instante;
		this.formaPagamento = formaPagamento;
		this.cliente = cliente;
	}

}
