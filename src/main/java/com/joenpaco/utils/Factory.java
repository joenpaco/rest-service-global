package com.joenpaco.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.joenpaco.models.Usuario;
import com.joenpaco.models.dtos.UsuarioDto;

public class Factory {

	public static UsuarioDto getUsuario() {
		
		UsuarioDto usuarioDto = null;
		
		try {
			
			usuarioDto = new ObjectMapper().readValue("{\"id\":3,\"username\":\"Manuel Parico\",\"password\":\"Manuel77\",\"enabled\":true,\"creatAt\":\"2020-03-20T12:41:30.312+0000\",\"updatedAt\":\"2020-03-20T12:41:30.312+0000\",\"roles\":[{\"id\":3,\"authority\":\"ROLE_USER\"}],\"email\":\"manuel@manuel.com\",\"phones\":[{\"number\":\"1234567\",\"citycode\":\"1\",\"countrycode\":\"57\",\"phoneId\":1}]}", UsuarioDto.class);
		
		} catch (IOException e) {

			throw new RuntimeException();
		}
		return usuarioDto;
	}
	
	public static List<UsuarioDto> getUsuarios(){
		
		UsuarioDto usuarioDto = null;
		UsuarioDto usuarioDto1 = null;
		UsuarioDto usuarioDto2 = null;
		
		try {
			
			usuarioDto = new ObjectMapper().readValue("{\"id\":1,\"username\":\"Manuel Parico\",\"password\":\"Manuel77\",\"enabled\":true,\"creatAt\":\"2020-03-20T12:41:30.312+0000\",\"updatedAt\":\"2020-03-20T12:41:30.312+0000\",\"roles\":[{\"id\":3,\"authority\":\"ROLE_USER\"}],\"email\":\"manuel@manuel.com\",\"phones\":[{\"number\":\"1234567\",\"citycode\":\"1\",\"countrycode\":\"57\",\"phoneId\":1}]}", UsuarioDto.class);
			usuarioDto1 = new ObjectMapper().readValue("{\"id\":2,\"username\":\"Manuel Parico\",\"password\":\"Manuel77\",\"enabled\":true,\"creatAt\":\"2020-03-20T12:41:30.312+0000\",\"updatedAt\":\"2020-03-20T12:41:30.312+0000\",\"roles\":[{\"id\":3,\"authority\":\"ROLE_USER\"}],\"email\":\"manuel@manuel.com\",\"phones\":[{\"number\":\"1234567\",\"citycode\":\"1\",\"countrycode\":\"57\",\"phoneId\":1}]}", UsuarioDto.class);
			usuarioDto2 = new ObjectMapper().readValue("{\"id\":3,\"username\":\"Manuel Parico\",\"password\":\"Manuel77\",\"enabled\":true,\"creatAt\":\"2020-03-20T12:41:30.312+0000\",\"updatedAt\":\"2020-03-20T12:41:30.312+0000\",\"roles\":[{\"id\":3,\"authority\":\"ROLE_USER\"}],\"email\":\"manuel@manuel.com\",\"phones\":[{\"number\":\"1234567\",\"citycode\":\"1\",\"countrycode\":\"57\",\"phoneId\":1}]}", UsuarioDto.class);
		
		} catch (IOException e) {

			throw new RuntimeException();
		}
		
		UsuarioDto[] arrayUsuariosDto = {usuarioDto, usuarioDto1, usuarioDto2};

		List<UsuarioDto> usuariosDto = Arrays.asList (arrayUsuariosDto);
		
		return usuariosDto;
	}
	
}
