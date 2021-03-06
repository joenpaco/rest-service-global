package com.joenpaco.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
						
//		when(usuarioRepository.findById(anyLong()).orElse(null)).thenReturn(usuarioFalso);
		doReturn(ousuario).when(usuarioRepository).findById(anyLong());
		
		// 2.- Act
		Usuario usuario = usuarioRepository.findById(1l).orElse(null);
		
		// 3.- Assert
		assertEquals(3, usuario.getId());
		
	}

	@Test
	void testFindByUsername() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto = new UsuarioDto();
		
		usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		when(usuarioRepository.findByUsername(anyString())).thenReturn(usuarioFalso);
		
		// 2.- Act
		Usuario usuario = usuarioRepository.findByUsername("Manuel Parico");
		
		// 3.- Assert
		assertEquals("Manuel Parico", usuario.getUsername());
		
	}

	@Test
	void testFindByEmail() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto;
		
		usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		when(usuarioRepository.findByEmail(anyString())).thenReturn(usuarioFalso);
		
		// 2.- Act
		Usuario usuario = usuarioRepository.findByEmail("manuel@manuel.com");
		
		// 3.- Assert
		assertEquals("manuel@manuel.com", usuario.getEmail());

	}

	@Test
	void testAddNewUsuario() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto;
		
		usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		when(usuarioRepository.save(usuarioFalso)).thenReturn(usuarioFalso);
		
		// 2.- Act
		Usuario usuario = usuarioRepository.save(usuarioFalso);
		
		// 3.- Assert
		assertEquals("Manuel Parico", usuario.getUsername());

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
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		// 3.- Assert
		assertEquals(3, usuarios.size());
		
		
	}

	@Test
	void testUpdateUsuario() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto;
		
		usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		when(usuarioRepository.save(usuarioFalso)).thenReturn(usuarioFalso);
		
		// 2.- Act
		Usuario usuario = usuarioRepository.save(usuarioFalso);
		
		// 3.- Assert
		assertEquals(3l, usuario.getId());

		
	}

	@Test
	void testDeleteById() {
		
		// 1.- Arrange
		UsuarioDto usuarioDto;
		
		usuarioDto = Factory.getUsuario();
		
		Usuario usuarioFalso = new ObjectMapper().convertValue(usuarioDto, Usuario.class);
		
		doNothing().when(usuarioRepository).delete(usuarioFalso);
		
		// 2.- Act
		usuarioRepository.delete(usuarioFalso);
		
		// 3.- Assert
		verify(usuarioRepository, times(1)).delete(usuarioFalso);

		
	}

}
