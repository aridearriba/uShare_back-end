package com.example.sardapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "profileImage")
    private byte[] image;

    public User() { }

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
        this.image = null;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public byte[] getImage()
    {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return "User [email=" + email + ", password=" + password + ", image=" + image.toString() + "]";
    }
}
