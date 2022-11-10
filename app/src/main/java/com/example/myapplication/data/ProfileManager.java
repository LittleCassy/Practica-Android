package com.example.myapplication.data;

import android.content.Context;

import com.example.myapplication.db.DDBBUser;

public class ProfileManager {

    private static String email;
    private static String username;
    private static int points;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ProfileManager.email = email;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ProfileManager.username = username;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        ProfileManager.points = points;
    }

    public static void updatePoints(int points, Context myCT) {
        ProfileManager.points = points;
        DDBBUser myDB = new DDBBUser(null);
        myDB.updatePoints(points, myCT);
    }

    public static void Reset(){
        email="";
        username="";
        points=0;
    }
}
