package com.joenpaco.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joenpaco.daos.UsuarioRepository;
import com.joenpaco.errors.UsuarioNameException;
import com.joenpaco.errors.UsuarioEmailException;
import com.joenpaco.errors.UsuarioNotFoundException;
import com.joenpaco.models.entities.Usuario;
import com.joenpaco.models.dtos.UsuarioDto;
import com.joenpaco.services.UsuarioService;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UsuarioServiceDaoImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioDto usuarioDto;
	
	@Override
	public UsuarioDto findById(String id) {
		
		Usuario usuario = usuarioRepository.findById(Long.parseLong(id)).orElse(null);
		
		if (usuario == null) {
			throw new UsuarioNotFoundException("id: " + id + " No Existe");
		}
		
		usuarioDto = new ObjectMapper().convertValue(usuario, UsuarioDto.class);
		
		return usuarioDto;
	}
	
	public void findByUsername(String nombre) {

		Usuario usuario = this.usuarioRepository.findByUsername(nombre);

		if (usuario != null) {

			throw new UsuarioNameException("El nombre: " + nombre + " ya registrado");

		}

	}
	
	public void findByEmail(String email) {
		
		Usuario usuario = this.usuarioRepository.findByEmail(email);
		
		if (usuario != null) {
			
			throw new UsuarioEmailException("El correo: " + email + " ya registrado");
			
		}
		
	}
	
	@Override
	public ResponseEntity<UsuarioDto> addNewUsuario(Usuario usuario) {
		
		findByUsername(usuario.getUsername());
		
		findByEmail(usuario.getEmail());
		
		usuarioRepository.save(usuario);
		
		usuarioDto = new ObjectMapper().convertValue(usuario, UsuarioDto.class);
		
		return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.CREATED);
	}
	
	@Override
	public List<UsuarioDto> findAll() {
		
		List<UsuarioDto> usuariosDtos = new ArrayList<UsuarioDto>();
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		for (Usuario usuario : usuarios) {
			
			usuarioDto = new ObjectMapper().convertValue(usuario, UsuarioDto.class);
			
			usuariosDtos.add(usuarioDto);
			
		}

		return usuariosDtos;
	}
	
	@Override
	public ResponseEntity<UsuarioDto> updateUsuario(Usuario usuario) {
		
		findById(Long.toString(usuario.getId()));
		
		usuarioRepository.save(usuario);
		
		usuarioDto = new ObjectMapper().convertValue(usuario, UsuarioDto.class);
		
		return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UsuarioDto> deleteById(Usuario usuario) {
		
		findById(Long.toString(usuario.getId()));
		
		usuarioRepository.delete(usuario);
		
		usuarioDto = new ObjectMapper().convertValue(usuario, UsuarioDto.class);
		
		return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.NO_CONTENT);
	}
	

}
