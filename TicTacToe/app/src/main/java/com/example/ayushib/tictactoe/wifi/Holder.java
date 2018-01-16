package com.example.ayushib.tictactoe.wifi;

import android.support.v7.widget.RecyclerView;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.ayushib.tictactoe.databinding.UserListItemBinding;
import com.example.ayushib.tictactoe.model.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.ayushib.tictactoe.Util.getCurrentUserId;


/**
 * Created by Ayushi B on 10-Jan-18.
 */

public class Holder extends RecyclerView.ViewHolder{
    public UserListItemBinding binding;

    public Holder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        binding.invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                db.child("users")
                        .child(getCurrentUserId())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                User me = dataSnapshot.getValue(User.class);

                                OkHttpClient client = new OkHttpClient();

                                String to = binding.getUser().getPushId();

                                Request request = new Request.Builder()
                                        .url(String
                                                .format("https://us-central1-tictactoe-64902.cloudfunctions.net/sendNotif/users/{name}/{pushId}", to, me.getPushId(), getCurrentUserId(), me.getName(), "invite"))
                                        .build();

                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {

                                    }
                                });
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        });
    }
}
