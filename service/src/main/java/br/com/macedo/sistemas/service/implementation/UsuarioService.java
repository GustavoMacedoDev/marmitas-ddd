package br.com.macedo.sistemas.service.implementation;

import java.util.Optional;

import br.com.macedo.sistemas.domain.aggregate.Usuario;

public interface UsuarioService {

	Optional<Usuario> buscarPorEmail(String email);
}
