package com.example.todolist.ToDo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class ToDo {


    @Id
    private Long id;

    private String contenu;

    private Date dateCreation;

    private String statut;

    public ToDo(long l, Date date, String statut) {

    }

    public ToDo(Long id, String contenu, Date dateCreation, String statut) {
        this.id = id;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}