package com.pumpkinapplabs.ordexpress.data.model;

import java.lang.ref.SoftReference;

public class Register {
    private int iduser;
    private String name;
    private String email;
    private String password;


    public Register(int iduser, String name, String email, String password) {
        this.iduser = iduser;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
