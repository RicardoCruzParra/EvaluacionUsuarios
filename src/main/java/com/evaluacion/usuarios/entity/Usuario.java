package com.evaluacion.usuarios.entity;

import javax.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

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

    @Column(name="email")
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

    public Usuario() {}

    @Override
    protected Object clone() throws CloneNotSupportedException {return super.clone();}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {super.finalize();}

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
