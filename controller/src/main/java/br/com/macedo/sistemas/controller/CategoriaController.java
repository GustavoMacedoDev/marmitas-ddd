package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.macedo.sistemas.domain.aggregate.Categoria;
import br.com.macedo.sistemas.domain.service.CategoriaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value = "/categoria", method = RequestMethod.GET)
	private @ResponseBody List<Categoria> listaTodasCategorias() {
		return this.categoriaService.findAll();
	}
	
	
	@RequestMapping(value = "/categoria", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Validated @RequestBody Categoria categoria) {
		
		categoria = categoriaService.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	
}
