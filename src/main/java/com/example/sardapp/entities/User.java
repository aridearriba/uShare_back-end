package com.example.sardapp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User
{
    @Id @Column(name = "email")             private String email;
    @Column(name = "password")              private String password;

    @Column(name = "profileImage")          private byte[] image;
    @Column(name = "imageType")             private String imageType;
    @Column(name = "name")                  private String name;
    @Column(name = "surname")               private String surname;
    @Column(name = "description")           private String description;
    @Column(name = "phoneNumber")           private Integer phoneNumber;
    @JsonFormat(pattern="yyyy-mm-dd")
    @Column(name = "birthday")              private Date birthday;
    @Column(name = "vehicle")               private Boolean vehicle;
    @Column(name = "comarca")               private String comarca;

    @Column(name = "aplecs")                private Boolean aplecs;
    @Column(name = "ballades")              private Boolean ballades;
    @Column(name = "concerts")              private Boolean concerts;
    @Column(name = "concursos")             private Boolean concursos;
    @Column(name = "cursets")               private Boolean cursets;
    @Column(name = "altres")                private Boolean altres;

    @Column(name = "comptarRepartir")       private Boolean comptarRepartir;
    @Column(name = "competidor")            private Boolean competidor;
    @Column(name = "coblaCompeticio")       private Boolean coblaCompeticio;

    public User() {}

    public String getEmail()
    {
        return email;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setImage(byte[] image)
    {
        this.image = image;
    }
}
