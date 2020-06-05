package br.com.macedo.sistemas.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.aggregate.Produto;
import br.com.macedo.sistemas.domain.dto.ProdutoNewDto;
import br.com.macedo.sistemas.domain.exceptions.ObjectNotFoundException;
import br.com.macedo.sistemas.domain.repository.ProdutoRepository;
import br.com.macedo.sistemas.domain.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    
	@Autowired
    private ProdutoRepository produtoRepository;
   
	@Override
    public List<Produto> findAll() {
        List<Produto> produtos = new ArrayList<>();
        produtos = produtoRepository.findAll();
        return produtos;
    }

	@Override
	public List<Produto> findAllAtivosByStatus(int status) {
		return this.produtoRepository.findAllByStatus(status);
	}

	@Override
	public Produto findById(Integer id) {
		
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	@Override
	public List<Produto> findByCategoriaId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto insert(ProdutoNewDto produtoNewDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto update(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto inativa(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> getProdutosQuiosque() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> getProdutosEntrega() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
