package com.example.demo.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Tareas")
public class Tasks {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "idtareas")
    private Integer idTask;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "nombre")
    private String name;
    @Column(name = "prioridad")
    private Integer priority;
    @ManyToOne
    @JoinColumn(name = "usuarios_idusuarios")
    private User user;
    @Column(name = "date")
    private Date date;
    @Column(name = "done")
    private Boolean done;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
