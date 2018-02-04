package com.example.ayushib.menuapp.chat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Amritesh on 16-Jan-18.
 */

public class Room {
    public ArrayList<String> member;
    public Map<String, String> groupInfo;

    public Room(){
        member = new ArrayList<>();
        groupInfo = new HashMap<String, String>();
    }

}
