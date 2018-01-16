package com.example.ayushib.menuapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    ImageView imageViewGame;
    ImageView imageViewDiary;
    ImageView imageViewNutrition;
    ImageView imageViewWorkout;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        imageViewGame = (ImageView) view.findViewById(R.id.imageViewGame);
        imageViewDiary = (ImageView) view.findViewById(R.id.imageViewDiary);
        imageViewNutrition = (ImageView) view.findViewById(R.id.imageViewNutrition);
        imageViewWorkout = (ImageView) view.findViewById(R.id.imageViewWorkout);

        imageViewGame.setOnClickListener(this);
        imageViewDiary.setOnClickListener(this);
        imageViewNutrition.setOnClickListener(this);
        imageViewWorkout.setOnClickListener(this);

        return view;
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.imageViewGame:
                Intent i = new Intent(getActivity(), CategoryActivity.class);
                startActivity(i);
                break;
            case R.id.imageViewDiary:
                Intent i2 = new Intent(getActivity(), DiaryActivity.class);
                startActivity(i2);
                break;
           case R.id.imageViewNutrition:
               Intent i3 = new Intent(getActivity(), NutritionActivity.class);
              startActivity(i3);
               break;
            default:
                Intent i4 = new Intent(getActivity(), WorkoutActivity.class);
                startActivity(i4);
                break;
        }



    }


}
