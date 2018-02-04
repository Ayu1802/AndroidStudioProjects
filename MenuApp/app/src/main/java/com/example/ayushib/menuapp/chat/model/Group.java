package com.example.ayushib.menuapp.chat.model;

/**
 * Created by Amritesh on 16-Jan-18.
 */

public class Group extends Room{
    public String id;
    public ListFriend listFriend;

    public Group(){
        listFriend = new ListFriend();
    }
}
