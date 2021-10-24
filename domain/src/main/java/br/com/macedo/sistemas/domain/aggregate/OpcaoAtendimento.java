package br.com.macedo.sistemas.domain.aggregate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "opcao_atendimento")
@Getter @Setter @EqualsAndHashCode
public class OpcaoAtendimento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String opcao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "opAtendimento")
	private List<Produto> produtos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "opAtendimento")
	private List<Pedido> pedidos = new ArrayList<>();
	
	public OpcaoAtendimento() {
	}


}
