package com.evaluacion.usuarios.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.evaluacion.usuarios.dao.UsuarioDAO;
import com.evaluacion.usuarios.entity.Usuario;
import com.evaluacion.usuarios.controller.UserJWTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.evaluacion.usuarios.util.ValidadorPatrones.emailValidator;
import static com.evaluacion.usuarios.util.ValidadorPatrones.passwordValidator;

@Service
public class UsuarioServiceImpl implements UsuarioService
{
    private final UsuarioDAO usuarioDAO;

    @Autowired
    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario findById(String id)
    {
        if(id == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no fue encontrado por el ID proporcionado");
        }
        return usuarioDAO.findById(id);
    }

    @Override
    public void save(Usuario usuario)
    {
        Date createDate = new Date();
        String id = UUID.randomUUID().toString();

        if(emailValidator(usuario.getEmail()))
        {
            if (passwordValidator(usuario.getPassword()))
            {
                usuario.setId(id);
                usuario.setCreated(createDate);
                usuario.setLast_login(createDate);
                usuario.setIsactive(true);

                usuarioDAO.save(usuario);
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contraseña debe tener al menos 1 mayúscula, 1 minuscula, 2 numeros");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email debe coincidir con el dominio: @dominio.cl");
        }
    }

    @Override
    public void deleteById(String id) {
        if(id == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no pudo ser eliminado por el ID proporcionado");
        }
            usuarioDAO.deleteById(id);
    }
}
