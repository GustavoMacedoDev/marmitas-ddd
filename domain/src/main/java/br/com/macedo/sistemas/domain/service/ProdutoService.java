package br.com.macedo.sistemas.domain.service;

import java.util.List;

import br.com.macedo.sistemas.domain.aggregate.Produto;
import br.com.macedo.sistemas.domain.dto.ProdutoNewDto;

public interface ProdutoService {

	List<Produto> findAll();
	
	List<Produto> findAllAtivosByStatus(int status);
	
	Produto findById(Integer id);
	
	List<Produto> findByCategoriaId(Integer id);
	
	Produto insert(ProdutoNewDto produtoNewDto);

	Produto update(Produto produto);
	
	Produto inativa(Integer id);
		
	List<Produto> getProdutosQuiosque();

	List<Produto> getProdutosEntrega();
}
