package com.evaluacion.usuarios.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.evaluacion.usuarios.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserJWTController
{
    @PostMapping("login")
    public Usuario login(@RequestBody Usuario user) {
        String token = getJWTToken(user.getName());
        Usuario usuario = new Usuario();
        usuario.setName(user.getName());
        usuario.setToken(token);
        return usuario;
    }

    public static String getJWTToken(String name) {
        String secretKey = "EvaluacionBCI";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("TokenTESTJWT")
                .setSubject(name)
                .claim("authorities", grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();

        return "Bearer " + token;
    }
}