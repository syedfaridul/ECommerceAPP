package com.flover.rifaecom.data;

public class UserData {
    private String Email;
    private String Password;

    public UserData(){}

    public UserData(String email, String password) {
        Email = email;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
