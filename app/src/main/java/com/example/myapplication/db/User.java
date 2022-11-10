package com.example.myapplication.db;

public class User {
    public String username="";
    public String email="";
    public int puntos=0;

    public User(String username, String email, int puntos){
        this.username = username;
        this.email = email;
        this.puntos = puntos;
    }

    public boolean isPresent() {
        if(username==""){
            return false;
        }else{
            return true;
        }
    }
}
