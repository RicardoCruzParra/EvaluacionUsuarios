package com.evaluacion.usuarios.dao;

import com.evaluacion.usuarios.entity.Usuario;

import java.util.List;

public interface UsuarioDAO {

    List<Usuario> findAll();

    Usuario findById(String id);

    void save(Usuario usuario);

    void deleteById(String id);
}
