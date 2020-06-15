package br.com.macedo.sistemas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.aggregate.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer>{

}
