package com.example.ayushib.menuapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;


    public SliderAdapter (Context context){
        this.context = context;
    }

    //Arrays
    public int [] slide_images = {
            R.drawable.memory,
            R.drawable.attention,
            R.drawable.vocabulary,
            R.drawable.probsolving

    };

    public String[] slide_headings = {
            "MEMORY GAMES",
            "ATTENTION GAMES",
            "LANGUAGE GAMES",
            "PROBLEM SOLVING GAMES"
    };

    public String[] slide_descrip ={
            "Test your retaining power",
            "Be attentive and quick",
            "Learning never stops",
            "Break the Ice"
    };


    public int[] lst_backgroundColor = {
            Color.rgb(100,181,246),
            Color.rgb(240,98,146),
            Color.rgb(0,150,136),
            Color.rgb(52,73,94)


    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        RelativeLayout layoutslide = (RelativeLayout) view.findViewById(R.id.slideLayout);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.imageView);
        TextView slideHeading = (TextView) view.findViewById(R.id.textViewName);
        TextView slideDescription = (TextView) view.findViewById(R.id.textViewDescription);

        layoutslide.setBackgroundColor(lst_backgroundColor[position]);


        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descrip[position]);

        container.addView(view);

        return  view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);

    }
}
