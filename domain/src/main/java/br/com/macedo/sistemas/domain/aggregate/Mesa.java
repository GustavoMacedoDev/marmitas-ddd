package br.com.macedo.sistemas.domain.aggregate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mesa", uniqueConstraints={@UniqueConstraint(columnNames={"numero_mesa"})})
@Getter @Setter @EqualsAndHashCode
public class Mesa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numero_mesa", unique = true)
	private String numeroMesa;
	
	@Column(name = "codigo_mesa")
	private int codigoMesa;
	
	@OneToMany(mappedBy = "mesa")
	@JsonIgnore
	private List<Pedido> pedidos = new ArrayList<>();
	
	@OneToMany(mappedBy = "mesa")
	@JsonIgnore
	private List<Pagamento> pagamentos = new ArrayList<>();
	
	@Column(name = "valor_pago_parcial")
	private double valorPagoParcial = 0;
	
	@Column(name = "total_mesa")
	private double totalMesa = 0;
	
	public Mesa() {
		// TODO Auto-generated constructor stub
	}

}
