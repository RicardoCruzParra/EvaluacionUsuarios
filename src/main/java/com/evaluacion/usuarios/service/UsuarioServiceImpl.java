package com.evaluacion.usuarios.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import com.evaluacion.usuarios.dao.UsuarioDAO;
import com.evaluacion.usuarios.entity.Usuario;
import com.evaluacion.usuarios.controller.UserJWTController;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService
{
    private final UsuarioDAO usuarioDAO;

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
            throw new RuntimeException("El usuario no fue encontrado por el ID proporcionado");
        }
        return usuarioDAO.findById(id);
    }

    @Override
    public void save(Usuario usuario)
    {
        Date createDate = new Date();
        String id = UUID.randomUUID().toString();
        final String PASSWORD_REGEX = "^(?=.*[0-9]{2})(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,16}$";
        final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

        if(usuario.getEmail().contains("@dominio.cl"))
        {
            if (PASSWORD_PATTERN.matcher(usuario.getPassword()).matches())
            {
                usuario.setId(id);
                usuario.setName(usuario.getName());
                usuario.setEmail(usuario.getEmail());
                usuario.setPassword(usuario.getPassword());
                usuario.setNumber(usuario.getNumber());
                usuario.setCityCode(usuario.getCityCode());
                usuario.setCountryCode(usuario.getCountryCode());
                usuario.setCreated(createDate);
                usuario.setLast_login(createDate);
                usuario.setToken(UserJWTController.getJWTToken(usuario.getName()));
                usuario.setIsactive(true);

                usuarioDAO.save(usuario);
            }
            else
            {
                throw new RuntimeException("La contraseña debe tener al menos 1 mayúscula, 1 minuscula, 2 numeros");
            }
        }
        else
        {
            throw new RuntimeException("El email debe coincidir con el dominio: @dominio.cl");
        }
    }

    @Override
    public void deleteById(String id) throws RuntimeException {
        usuarioDAO.deleteById(id);
        if(id == null)
        {
            throw new RuntimeException("El usuario no pudo ser eliminado por el ID proporcionado ");
        }
    }
}
