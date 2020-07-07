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

import br.com.macedo.sistemas.domain.aggregate.Adicional;
import br.com.macedo.sistemas.domain.service.AdicionalService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AdicionalController {
	
	@Autowired
	private AdicionalService adicionalService;
	
	
	@RequestMapping(value = "/adicionais", method = RequestMethod.GET)
	private @ResponseBody List<Adicional> listaTodosAdicionais() {
		return this.adicionalService.findAll();
	}
	
	@RequestMapping(value = "/adicional", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Validated @RequestBody Adicional adicional) {
		
		adicional = adicionalService.save(adicional);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(adicional.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	

}
