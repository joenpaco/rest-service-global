package com.joenpaco.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.joenpaco.models.dtos.UsuarioDto;
import com.joenpaco.models.entities.Usuario;


public interface UsuarioService {
	
	public abstract List<UsuarioDto> findAll();
	
	public abstract UsuarioDto findById(String id);
	
	public abstract ResponseEntity<UsuarioDto> addNewUsuario(Usuario usuario);
	
	public abstract ResponseEntity<UsuarioDto> updateUsuario(Usuario usuario);
	
	public abstract ResponseEntity<UsuarioDto> deleteById(Usuario usuario);

}
