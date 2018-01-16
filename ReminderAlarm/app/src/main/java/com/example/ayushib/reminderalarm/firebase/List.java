package com.example.ayushib.reminderalarm.firebase;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

import com.example.ayushib.reminderalarm.util.Helper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class List <Child extends ListItem> {

    static Helper helper;
    static public Helper getHelper() {
        if(helper == null)
            helper = Helper.getInstance();
        return helper;
    }

    private DatabaseReference reference;

    public DatabaseReference getReference() {
        return reference;
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }

    public List(String childPath, String uId) {
        FirebaseDatabase fdb = getHelper().getDatabase();
        if(uId != null)
            childPath = childPath + "/" + uId;
        setReference(fdb.getReference(childPath));
    }

    public void add(Child item, CreateListener listener) {
        item.writeTo(this, listener);
    }

    public void remove(Child item, RemoveListener listener) {
        item.removeFrom(this, listener);
    }



}
