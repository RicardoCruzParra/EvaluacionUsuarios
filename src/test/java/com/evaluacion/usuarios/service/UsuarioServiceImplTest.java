package com.evaluacion.usuarios.service;

import com.evaluacion.usuarios.dao.UsuarioDAO;
import com.evaluacion.usuarios.entity.Usuario;
import com.evaluacion.usuarios.error.ErrorHandler;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UsuarioServiceImplTest {

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioDAO usuarioDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @org.junit.Test
    public void consultaExitosa_ListaUsuarios() {
        UsuarioServiceImpl usuarioServiceImpl = new UsuarioServiceImpl(usuarioDAO);
        List<Usuario> listaUsuario = new ArrayList<>();
        Usuario usuario = new Usuario();
        listaUsuario.add(usuario);
        when(restTemplateMock.postForObject(anyString(), any(HttpEntity.class), any())).
                thenReturn(listaUsuario);
        List<Usuario> respuesta = usuarioServiceImpl.findAll();
        Assertions.assertEquals(listaUsuario, respuesta);
    }

    @org.junit.Test(expected = ErrorHandler.class)
    public void consultaExitosa_PlataformaBaseException() {
        UsuarioServiceImpl usuarioServiceImpl = new UsuarioServiceImpl(usuarioDAO);
        List<Usuario> listaUsuario = new ArrayList<>();
        Usuario usuario = new Usuario();
        listaUsuario.add(usuario);
        when(restTemplateMock.postForObject(anyString(), any(HttpEntity.class), (Class<Object>) any())).
                thenThrow(ErrorHandler.class);
        List<Usuario> respuesta = usuarioServiceImpl.findAll();
    }

    @org.junit.Test
    public void creaUsuarioPostObjectOK(){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Usuario u = new Usuario();
        usuarios.add(u);
        when(restTemplateMock.postForObject(anyString(), (HttpEntity<List<Usuario>>) any(), (Class<Usuario>) any())).
                thenReturn(null);
    }

    @org.junit.Test
    public void creaSolicitudServiceBad() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        ResponseEntity<List<Usuario>> res = new ResponseEntity(usuarios, HttpStatus.BAD_REQUEST);
        when(restTemplateMock.postForObject(anyString(), any(HttpEntity.class), (Class<Usuario>) any())).
                thenThrow(ErrorHandler.class);
    }
}