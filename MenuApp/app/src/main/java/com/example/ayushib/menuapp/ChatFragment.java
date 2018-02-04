package com.example.ayushib.menuapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ayushib.menuapp.chat.ui.UserLoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements View.OnClickListener {

    Button btnchatstart;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chat, container, false);

        btnchatstart = (Button) view.findViewById(R.id.btnchatstart);

        btnchatstart.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(getActivity(), UserLoginActivity.class);
        getActivity().startActivity(i);
    }
}
