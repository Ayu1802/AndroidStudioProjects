package com.example.ayushib.reminderalarm.firebase;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public class ListItem <Parent extends List> extends Item {



    protected void setReference(Parent parent) {
        DatabaseReference pr = parent.getReference();
        if (getKey() == null) {
            setReference(pr.push());
            setKey(getReference().getKey());
        } else {
            setReference(pr.child(getKey()));
        }
    }

    public void writeTo(Parent parent, CreateListener listener) {
        setReference(parent);
        super.writeIn(listener);
    }

    public void writeTo(Parent parent, DatabaseReference.CompletionListener listener) {
        setReference(parent);
        super.writeIn(listener);
    }

    public void removeFrom(Parent parent, RemoveListener listener) {
        setReference(parent);
        super.removeIn(listener);
    }

}
