package com.evaluacion.usuarios.service;

import java.util.List;

import com.evaluacion.usuarios.entity.Usuario;

public interface UsuarioService
{
    List<Usuario> findAll();

    Usuario findById(String id);

    void save(Usuario usuario);

    void deleteById(String id);
}
