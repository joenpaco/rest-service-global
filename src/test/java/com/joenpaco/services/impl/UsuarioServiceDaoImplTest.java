package com.joenpaco.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.joenpaco.errors.UsuarioEmailException;
import com.joenpaco.errors.UsuarioNameException;
import com.joenpaco.errors.UsuarioNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joenpaco.daos.UsuarioRepository;
import com.joenpaco.models.dtos.UsuarioDto;
import com.joenpaco.models.entities.Usuario;
import com.joenpaco.utils.Factory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class UsuarioServiceDaoImplTest {
	
	@InjectMocks
	UsuarioServiceDaoImpl usuarioServiceDaoImpl;

	@Mock
	private UsuarioRepository usuarioRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFindById() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto = new UsuarioDto();
		
		usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		Optional<Usuario> ousuario = Optional.of(usuarioFalso);

		doReturn(ousuario).when(usuarioRepository).findById(anyLong());
		
		// 2.- Act
		UsuarioDto usuario = usuarioServiceDaoImpl.findById("1");
		
		// 3.- Assert
		assertEquals(3, usuario.getId());
		
	}

	@Test
	public void findByIdUsuarioNotFound() {

		// 1.- Arrange
		Usuario usuarioFalso = null;

		Optional<Usuario> ousuario = Optional.ofNullable(usuarioFalso);

		doReturn(ousuario).when(usuarioRepository).findById(anyLong());

		assertThrows(UsuarioNotFoundException.class, () -> usuarioServiceDaoImpl.findById("1"));

	}

	@Test
	void testFindByUsername() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto = new UsuarioDto();
		
		usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper()
				.convertValue(usuarioDto, Usuario.class);
		
		when(usuarioRepository.findByUsername(anyString()))
				.thenReturn(usuarioFalso);

		assertThrows(UsuarioNameException.class, () -> usuarioServiceDaoImpl
				.findByUsername("Manuel Parico"));
		
	}

	@Test
	void testFindByEmail() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto;
		
		usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		when(usuarioRepository.findByEmail(anyString())).thenReturn(usuarioFalso);

		assertThrows(UsuarioEmailException.class, () -> usuarioServiceDaoImpl
				.findByEmail("test@test.com"));

	}

	@Test
	void testAddNewUsuario() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		when(usuarioRepository.save(usuarioFalso)).thenReturn(usuarioFalso);
		
		// 2.- Act
		ResponseEntity<UsuarioDto> usuarioActual = usuarioServiceDaoImpl.addNewUsuario(usuarioFalso);
		
		// 3.- Assert
		assertEquals("Manuel Parico", usuarioActual.getBody().getUsername());

	}

	@Test
	void testFindAll() {
		
		// 1.- Arrange
		Usuario usuario;
		List<Usuario> usuariosFalsos = new ArrayList<Usuario>();
		
		List<UsuarioDto> usuariosDto = Factory.getUsuarios();
		
		for (UsuarioDto usuarioDto : usuariosDto) {
			usuario = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
			usuariosFalsos.add(usuario);
		}
		
		when(usuarioRepository.findAll()).thenReturn(usuariosFalsos);
		
		// 2.- Act
		List<UsuarioDto> usuarios = usuarioServiceDaoImpl.findAll();
		
		// 3.- Assert
		assertEquals(3, usuarios.size());
		
		
	}

	@Test
	void testUpdateUsuario() {
		// 1.- Arrange
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto = Factory.getUsuario();
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		Optional<Usuario> ousuario = Optional.of(usuarioFalso);
		when(usuarioRepository.findById(usuarioFalso.getId())).thenReturn(ousuario);

		// 2.- Action
		ResponseEntity<UsuarioDto> usuarioActual = usuarioServiceDaoImpl.updateUsuario(usuarioFalso);

		// 3.- Assert
		assertEquals(usuarioFalso.getId(), usuarioActual.getBody().getId());
		
	}

	@Test
	void testDeleteById() {

		// 1.- Arrange
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto = Factory.getUsuario();
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		Optional<Usuario> ousuario = Optional.of(usuarioFalso);
		when(usuarioRepository.findById(usuarioFalso.getId())).thenReturn(ousuario);

		// 2.- Action
		ResponseEntity<UsuarioDto> usuarioActual = usuarioServiceDaoImpl.deleteById(usuarioFalso);

		// 3.- Assert
		assertEquals(usuarioFalso.getId(), usuarioActual.getBody().getId());
		
	}

}
