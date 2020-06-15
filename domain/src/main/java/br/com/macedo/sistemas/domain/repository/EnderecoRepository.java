package br.com.macedo.sistemas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.aggregate.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
