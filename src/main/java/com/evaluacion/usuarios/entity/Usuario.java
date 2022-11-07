package com.evaluacion.usuarios.entity;

import javax.persistence.*;
import java.util.Date;

import lombok.*;

@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="email", unique=true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="number")
    private String number;

    @Column(name="cityCode")
    private String cityCode;

    @Column(name="countryCode")
    private String countryCode;

    @Column(name="created")
    private Date created;

    @Column(name="last_login")
    private Date last_login;

    @Column(name="token")
    private String token;

    @Column(name="isactive")
    private boolean isactive;
}
