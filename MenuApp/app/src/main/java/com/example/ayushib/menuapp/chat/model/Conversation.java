package com.example.ayushib.menuapp.chat.model;

import java.util.ArrayList;
/**
 * Created by Amritesh on 16-Jan-18.
 */

public class Conversation {

    private ArrayList<Message> listMessageData;
    public Conversation(){
        listMessageData = new ArrayList<>();
    }

    public ArrayList<Message> getListMessageData() {
        return listMessageData;
    }
}


