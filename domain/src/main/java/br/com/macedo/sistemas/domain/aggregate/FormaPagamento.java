package br.com.macedo.sistemas.domain.aggregate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "forma_pagamento")
@Getter @Setter @EqualsAndHashCode
public class FormaPagamento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "forma_pagamento")
	private String formaPagamento;
	
	@JsonIgnore
	@OneToMany(mappedBy="formaPagamento", cascade=CascadeType.ALL)
	private List<Pedido> pedidos;
	
	@JsonIgnore
	@OneToMany(mappedBy="formaPagamento", cascade=CascadeType.ALL)
	private List<Pagamento> pagamentos;
	
	public FormaPagamento() {
		// TODO Auto-generated constructor stub
	}


}