package com.example.sandilemazibuko.grooveapp_beta_01;

/**
 * Created by sandilemazibuko on 15/09/17.
 */
public class User {



    public String id;
    public String name;
    public String surname;
    public String idNumber;
    public String email;
    public String cellphone;
    public String username;

    public User(String id,String name,String surname, String idNumber,
                String email,String cellphone,String username, String password){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
        this.email = email;
        this.cellphone = cellphone;
        this.username = username;
        this.password = password;
    }

    public User(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        this.username = username;
        return username;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String password;



}
