package com.example.ayushib.reminderalarm;


import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import com.firebase.ui.database.FirebaseRecyclerAdapter;


import com.example.ayushib.reminderalarm.firebase.Item;



/**
 * Created by Ayushi B on 15-Jan-18.
 */

public abstract class RecyclerAdapter <T extends Item, VH extends RecyclerView.ViewHolder> extends com.firebase.ui.database.FirebaseRecyclerAdapter<T, VH> {

    public RecyclerAdapter(Class<T> modelClass, int modelLayout, Class<VH> viewHolderClass, DatabaseReference ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    public RecyclerAdapter(Class<T> modelClass, int modelLayout, Class<VH> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected T parseSnapshot(DataSnapshot snapshot) {
        T item = super.parseSnapshot(snapshot);
        item.setKey(snapshot.getKey());
        item.setPriority(snapshot.getPriority());
        return item;
    }
}
