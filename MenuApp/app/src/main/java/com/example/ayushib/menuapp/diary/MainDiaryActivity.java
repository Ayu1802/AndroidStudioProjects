package com.example.ayushib.menuapp.diary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayushib.menuapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class MainDiaryActivity extends AppCompatActivity {

    private RecyclerView diaryList;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_diary);
        database = FirebaseDatabase.getInstance().getReference().child("Diary");

        diaryList = (RecyclerView) findViewById(R.id.diary_list);
        diaryList.setHasFixedSize(true);
        diaryList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Diary,DiaryViewHolder> FirebaseRecylerAdapter = new FirebaseRecyclerAdapter<Diary, DiaryViewHolder>(

                Diary.class,
                R.layout.diary_row,
                DiaryViewHolder.class,
                database

        ) {
            @Override
            protected void populateViewHolder(DiaryViewHolder viewHolder, Diary model, int position) {


                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(getApplicationContext(),model.getImage());
            }
        };

        diaryList.setAdapter(FirebaseRecylerAdapter);
    }


    public static class DiaryViewHolder extends RecyclerView.ViewHolder{

        View view;

        public DiaryViewHolder(View itemView) {
            super(itemView);

            view = itemView;
        }

        public void setTitle(String title){

            TextView post_Title = (TextView) view.findViewById(R.id.post_Title);
            post_Title.setText(title);
        }

        public void setDescription(String description){

            TextView post_Description = (TextView) view.findViewById(R.id.post_Description);
            post_Description.setText(description);
        }

        public void setImage(Context ctx, String image){

            ImageView post_Image = (ImageView) view.findViewById(R.id.post_Image);
            Picasso.with(ctx).load(image).into(post_Image);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== R.id.action_add)

            startActivity(new Intent(MainDiaryActivity.this , PostActivity.class));

        return super.onOptionsItemSelected(item);
    }
}

