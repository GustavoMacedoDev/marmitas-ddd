package br.com.macedo.sistemas.domain.service;

import java.util.Optional;

import br.com.macedo.sistemas.domain.aggregate.Usuario;

public interface UsuarioService {

	Optional<Usuario> buscarPorEmail(String email);
}
