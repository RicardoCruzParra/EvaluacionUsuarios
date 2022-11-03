package com.evaluacion.usuarios.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

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

    public Usuario(String id, String name, String password, String email, String number, String cityCode, String countryCode,
                   Date created, Date last_login, String token, boolean isactive) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.created =  created;
        this.last_login = last_login;
        this.token = token;
        this.isactive = isactive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Date getCreated() {return created;}

    public void setCreated(Date created) {this.created = created;}

    public Date getLast_login() {return last_login;}

    public void setLast_login(Date last_login) {this.last_login = last_login;}

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}

    public boolean isIsactive() {return isactive;}

    public void setIsactive(boolean isactive) {this.isactive = isactive;}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
