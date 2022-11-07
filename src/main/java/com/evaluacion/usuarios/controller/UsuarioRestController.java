package com.evaluacion.usuarios.controller;

import java.util.List;
import javax.validation.Valid;

import com.evaluacion.usuarios.entity.Usuario;
import com.evaluacion.usuarios.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class UsuarioRestController
{
    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/usuario",  produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity< List<Usuario>> findAll(){
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value= "/usuarios/{usuarioID}" , consumes= {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Usuario> getUsuario(@PathVariable String usuarioID){
        return new ResponseEntity<>(usuarioService.findById(usuarioID), HttpStatus.OK);
    }

    @PostMapping( value = "/usuarios", consumes= {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Usuario> agregarUsuario(@Valid @RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping(value ="usuarios/{usuariosID}", consumes= {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity eliminarUsuario(@PathVariable String usuariosID) {
        usuarioService.deleteById(usuariosID);
        return new ResponseEntity(HttpStatus.OK);
    }
}
