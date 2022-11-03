package com.evaluacion.usuarios.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.evaluacion.usuarios.entity.Usuario;
import com.evaluacion.usuarios.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

public class UsuarioRestController {

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
        Usuario usuario = usuarioService.findById(usuarioID);

        if(usuario == null) {
            throw new RuntimeException("Usuario con el ID : "+usuarioID +" No encontrado");
        }
        return new ResponseEntity<>(usuarioService.findById(usuarioID), HttpStatus.OK);
    }

    @PostMapping( value = "/usuarios", consumes= {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario) {
        Usuario user= new Usuario();
        Date createDate = new Date();
        String id = UUID.randomUUID().toString();
        String KEY ="EvaluacionRC";
        String tokenJWT = Jwts.builder()
                            .signWith(SignatureAlgorithm.HS256, KEY)
                            .setSubject(usuario.getName())
                            .claim("Email", usuario.getEmail())
                            .claim("Telefono", usuario.getNumber())
                            .compact();

        user.setId(id);
        user.setName(usuario.getName());
        user.setEmail(usuario.getEmail());
        user.setPassword(usuario.getPassword());
        user.setNumber(usuario.getNumber());
        user.setCityCode(usuario.getCityCode());
        user.setCountryCode(usuario.getCountryCode());
        user.setCreated(createDate);
        user.setLast_login(createDate);
        user.setToken(tokenJWT);
        user.setIsactive(true);

        usuarioService.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value ="usuarios/{usuariosID}", consumes= {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity eliminarUsuario(@PathVariable String usuariosID) {
        Usuario usuario = usuarioService.findById(usuariosID);
        if(usuario == null) {
            throw new RuntimeException("Usuario no encontrado por el  Id -"+usuariosID);
        }
        usuarioService.deleteById(usuariosID);
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
