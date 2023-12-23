package com.redsoft.curso.cursojava.servicio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redsoft.curso.cursojava.modelo.Usuario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private static final String RUTAJSON = "C:\\Dev\\cursojava-main\\src\\main\\resources\\json\\usuario.json";

    public List<Usuario> findAllUsuarios() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Usuario> usuarios = mapper.readValue(new File(RUTAJSON), new TypeReference<List<Usuario>>() {
            });
            return usuarios;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void agregarUsuario(Usuario nuevoUsuario) {
        ObjectMapper mapper = new ObjectMapper();
        File archivoJson = new File(RUTAJSON);
        try {
            // Leer la lista actual de usuarios del archivo JSON
            List<Usuario> usuarios = new ArrayList<>();
            if (archivoJson.exists()) {
                usuarios = mapper.readValue(archivoJson, new TypeReference<List<Usuario>>() {
                });
            }
            // Agregar el nuevo usuario a la lista
            usuarios.add(nuevoUsuario);
            // Escribir la lista actualizada de vuelta al archivo JSON
            mapper.writeValue(archivoJson, usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrarUsuario(int idUsuario) {
        ObjectMapper mapper = new ObjectMapper();
        File archivoJson = new File(RUTAJSON);

        try {
            // Leer la lista actual de usuarios del archivo JSON
            List<Usuario> usuarios = new ArrayList<>();
            if (archivoJson.exists()) {
                usuarios = mapper.readValue(archivoJson, new TypeReference<List<Usuario>>() {
                });
            }

            // Filtrar la lista para excluir el usuario con el ID proporcionado
            List<Usuario> usuariosActualizados = usuarios.stream()
                    .filter(usuario -> usuario.getId() != idUsuario)
                    .collect(Collectors.toList());

            // Escribir la lista actualizada de vuelta al archivo JSON
            mapper.writeValue(archivoJson, usuariosActualizados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}