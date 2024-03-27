package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "idusuarios")
    private Integer idUsuarios;

    private String usuario;

    @Column(name = "mail")
    private String email;

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setId(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUsuarios=" + idUsuarios +
                ", usuario='" + usuario + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
