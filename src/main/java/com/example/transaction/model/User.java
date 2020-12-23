package com.example.transaction.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    private String firstname;
    private String surname;
    @Id
    private String username;
    private String passW;

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassW() {
        return passW;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassW(String passW) {
        this.passW = passW;
    }
}
