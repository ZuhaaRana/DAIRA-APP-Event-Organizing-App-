package com.example.smd_project.mentors;

import android.app.Application;

public class Mentor extends Application {

    public static String password;
    public static String name;
    public static String email;

    public Mentor(){

    }

    public static void setEmail(String email) {
        Mentor.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public Mentor(String name, String password,String email){
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public  String getName() {
        return name;
    }

    public  void setName(String name) {
        Mentor.name = name;
    }

    public  String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        Mentor.password = password;
    }
}
