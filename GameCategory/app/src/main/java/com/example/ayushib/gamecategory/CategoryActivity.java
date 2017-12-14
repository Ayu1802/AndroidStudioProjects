package com.example.ayushib.gamecategory;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

        private ViewPager slideViewpager;
        private LinearLayout dotLayout;
        private TextView[] dots;
        private SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        slideViewpager= (ViewPager) findViewById(R.id.slideViewPager);
        dotLayout = (LinearLayout) findViewById(R.id.dotsLayout);


        sliderAdapter = new SliderAdapter(this);

        slideViewpager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        slideViewpager.addOnPageChangeListener(viewListener);

    }

    public void addDotsIndicator(int position){
        dots = new TextView[4];
        dotLayout.removeAllViews(); //not to create multiple number of dots

        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            dotLayout.addView(dots[i]);

        }

        if(dots.length >0){
            dots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

        ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i); //get position of the selected slide
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
