package com.example.ayushib.puzzlemaster;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Ayushi B on 29-Jan-18.
 */

public class CustomAdapter extends BaseAdapter {

    private ArrayList<Button> mbuttons = null;
    private int mColumnWidth, mColumnHeight;

    public CustomAdapter(ArrayList<Button> buttons, int columnWidth, int columnHeight) {
       mbuttons = buttons;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
    }


    @Override
    public int getCount() {
     return mbuttons.size();
    }

    @Override
    public Object getItem(int position) {
        return (Object) mbuttons.get(position);
    }

    @Override
    public long getItemId(int position){return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            button = mbuttons.get(position);
        } else {
            button = (Button) convertView;
        }

        android.widget.AbsListView.LayoutParams params =
                new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnHeight);
        button.setLayoutParams(params);

        return button;
    }



}
