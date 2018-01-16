package com.example.ayushib.tictactoe.model;

/**
 * Created by Ayushi B on 10-Jan-18.
 */

public class User {
    private String name, pushId;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
