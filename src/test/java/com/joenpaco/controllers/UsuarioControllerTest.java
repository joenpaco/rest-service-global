package com.joenpaco.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joenpaco.models.entities.Usuario;
import com.joenpaco.models.dtos.UsuarioDto;
import com.joenpaco.services.UsuarioService;
import com.joenpaco.utils.Factory;

class UsuarioControllerTest {
	
	@InjectMocks
	private UsuarioController usuarioController;
	
	@Mock
	private UsuarioService usuarioService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAddNewUsuario() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto;
		
		usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		ResponseEntity<UsuarioDto> usuarioResponse = new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.CREATED);
		
		when(usuarioService.addNewUsuario(usuarioFalso)).thenReturn(usuarioResponse);
		
		// 2.- Act
		ResponseEntity<UsuarioDto> usuario = usuarioController.addNewUsuario(usuarioFalso);
		
		// 3.- Assert
		assertEquals("Manuel Parico", usuario.getBody().getUsername());

		
	}

	@Test
	void testFindAllUsuarios() {
		
		// 1.- Arrange
		List<UsuarioDto> usuariosDto = Factory.getUsuarios();
		
		when(usuarioService.findAll()).thenReturn(usuariosDto);
		
		// 2.- Act
		List<UsuarioDto> usuarios = usuarioController.findAllUsuarios();
		
		// 3.- Assert
		assertEquals(3, usuarios.size());

		
	}

	@Test
	void testUpdate() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto;
		
		usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		ResponseEntity<UsuarioDto> usuarioResponse = new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.OK);
		
		when(usuarioService.updateUsuario(usuarioFalso)).thenReturn(usuarioResponse);
		
		// 2.- Act
		ResponseEntity<UsuarioDto> usuario = usuarioController.update(usuarioFalso);
		
		// 3.- Assert
		assertEquals(3, usuario.getBody().getId());

	}

	@Test
	void testDeleteById() {
		
		// 1.- Arrange
//		Usuario usuarioFalso;
		
		UsuarioDto usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		ResponseEntity<UsuarioDto> usuarioResponse = new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.GONE);
		
		when(usuarioService.deleteById(usuarioFalso)).thenReturn(usuarioResponse);
		
		// 2.- Act
		ResponseEntity<UsuarioDto> usuario = usuarioController.deleteById(usuarioFalso);
		
		// 3.- Assert
		assertEquals(410, usuario.getStatusCodeValue());

		
	}

}
