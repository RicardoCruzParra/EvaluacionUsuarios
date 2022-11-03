package com.evaluacion.usuarios.service;

import java.util.List;

import com.evaluacion.usuarios.dao.UsuarioDAO;
import com.evaluacion.usuarios.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario findById(String id) {
        return usuarioDAO.findById(id);
    }

    @Override
    public void save() {
        save();
    }

    @Override
    public void save(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public void deleteById(String id) {
        usuarioDAO.deleteById(id);
    }
}
