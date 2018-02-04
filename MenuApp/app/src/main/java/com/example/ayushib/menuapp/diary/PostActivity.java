package com.example.ayushib.menuapp.diary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ayushib.menuapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    private ImageButton mSelectImage;

    private EditText mPostTitle;

    private EditText mPostDesc;

    private Button mSubmitBtn;

    private Uri imgUri = null;

    private StorageReference mStorage ;

    private ProgressDialog progress;

    private DatabaseReference db;

    private static final int GALLERY_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mStorage = FirebaseStorage.getInstance().getReference();

        db = FirebaseDatabase.getInstance().getReference().child("Diary");

        mSelectImage=(ImageButton) findViewById(R.id.imageSelect);

        mPostTitle = (EditText) findViewById(R.id.titleField);

        mPostDesc = (EditText) findViewById(R.id.descField);

        mSubmitBtn =(Button) findViewById(R.id.submitBtn);

        progress = new ProgressDialog(this);




        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);
            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPosting();
            }
        });
    }

    private void startPosting() {

        progress.setMessage("Posting to My Diary");


        final String titleValue = mPostTitle.getText().toString().trim();
        final String descriptionValue = mPostDesc.getText().toString().trim();

        if ((!TextUtils.isEmpty(titleValue) && (!TextUtils.isEmpty(descriptionValue)) && (imgUri != null))) {

            StorageReference file = mStorage.child("My_Diary");

            progress.show();

            file.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    @SuppressWarnings("VisibleForTests") Uri downloadURI = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPost = db.push();

                    newPost.child("title").setValue(titleValue);
                    newPost.child("description").setValue(descriptionValue);
                    newPost.child("image").setValue(downloadURI.toString());

                    progress.dismiss();
                    Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();

                }
            });

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

            imgUri = data.getData();

            mSelectImage.setImageURI(imgUri);
        }
    }
}