package br.com.macedo.sistemas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.aggregate.OpcaoAtendimento;

public interface OpcaoAtendimentoRepository extends JpaRepository<OpcaoAtendimento, Integer>{

}
