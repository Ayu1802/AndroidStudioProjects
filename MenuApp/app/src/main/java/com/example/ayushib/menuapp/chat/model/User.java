package com.example.ayushib.menuapp.chat.model;

/**
 * Created by Amritesh on 16-Jan-18.
 */

public class User {
    public String name;
    public String email;
    public String avata;
    public Status status;
    public Message message;


    public User(){
        status = new Status();
        message = new Message();
        status.isOnline = false;
        status.timestamp = 0;
        message.idReceiver = "0";
        message.idSender = "0";
        message.text = "";
        message.timestamp = 0;
    }
}

