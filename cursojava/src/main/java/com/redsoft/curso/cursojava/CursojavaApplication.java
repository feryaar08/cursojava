package com.redsoft.curso.cursojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.redsoft.curso.cursojava.modelo.Usuario;
import com.redsoft.curso.cursojava.servicio.UsuarioService;

@SpringBootApplication
public class CursojavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursojavaApplication.class, args);

		Usuario usuario = new Usuario();
		usuario.setId(4);
		usuario.setNombre("Lalo");
		usuario.setCorreo("correo2@example.com");
		usuario.setContrasena("contraseña2");

		UsuarioService servicio = new UsuarioService();
		// servicio.agregarUsuario(usuario);
		servicio.findAllUsuarios();

	}

}