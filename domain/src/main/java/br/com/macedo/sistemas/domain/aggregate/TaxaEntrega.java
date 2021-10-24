package br.com.macedo.sistemas.domain.aggregate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taxa_entrega")
@Getter @Setter @EqualsAndHashCode
public class TaxaEntrega implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	private Double valor;
	
	public TaxaEntrega() {
		// TODO Auto-generated constructor stub
	}
	
}
