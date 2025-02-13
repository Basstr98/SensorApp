package com.ticarum.apirest.login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
	public Usuario getUsuarioByNombre(String nombre);
}
