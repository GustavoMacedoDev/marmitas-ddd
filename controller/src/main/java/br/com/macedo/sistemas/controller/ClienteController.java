package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.macedo.sistemas.domain.aggregate.Cliente;
import br.com.macedo.sistemas.domain.dto.ClienteNewDto;
import br.com.macedo.sistemas.domain.service.ClienteService;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public List<Cliente> listaClientes() {
		
		return this.clienteService.findAll();
		
	}
	
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public Cliente getClienteById(@Validated @PathVariable Integer id) {
		
		return this.clienteService.findById(id);
		
	}
	
	@RequestMapping(value = "/cadastrar-pf",method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Validated @RequestBody ClienteNewDto objDto) {
		Cliente obj = clienteService.fromDTO(objDto);
		obj = clienteService.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/telefone", method=RequestMethod.GET)
	public Cliente findAll(@RequestParam(value="value") Long telefone) {
		
		Cliente cliente = clienteService.findByTelefone(telefone);
		
		return cliente;
	}
	
	
	
	

}
