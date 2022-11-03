package com.evaluacion.usuarios.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.evaluacion.usuarios.entity.Usuario;

@SuppressWarnings("ALL")
@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    private final EntityManager entityManager;

    protected UsuarioDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Usuario> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("From Usuario");

        return (List<Usuario>) theQuery.list();
    }

    @Override
    public Usuario findById(String id) {
        Session currentSession = entityManager.unwrap(Session.class);

        return currentSession.get(Usuario.class, id);
    }

    @Override
    public void save(Usuario usuario) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(usuario);
    }

    @Override
    public void deleteById(String id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from Usuario where id=:clienteId");

        theQuery.setParameter("usuarioId", id);
        theQuery.executeUpdate();
    }
}
