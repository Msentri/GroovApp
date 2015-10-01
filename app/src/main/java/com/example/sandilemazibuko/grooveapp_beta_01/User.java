package com.example.sandilemazibuko.grooveapp_beta_01;

/**
 * Created by sandilemazibuko on 15/09/17.
 */
public class User {

    String user_id;
    String user_name;
    String user_surname;
    String user_picture;
    String user_password;
    String user_email;
    String user_dob;
    String user_membership_type;

    public User(String user_id, String user_name,
                String user_surname, String user_picture,
                String user_password, String user_email,
                String user_dob, String user_membership_type) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_picture = user_picture;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_dob = user_dob;
        this.user_membership_type = user_membership_type;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_surname='" + user_surname + '\'' +
                ", user_picture='" + user_picture + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_dob='" + user_dob + '\'' +
                ", user_membership_type='" + user_membership_type + '\'' +
                '}';
    }



    public String getUser_membership_type() {
        return user_membership_type;
    }

    public void setUser_membership_type(String user_membership_type) {
        this.user_membership_type = user_membership_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_dob() {
        return user_dob;
    }

    public void setUser_dob(String user_dob) {
        this.user_dob = user_dob;
    }








}
