package com.example.agencyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_art extends AppCompatActivity {
    private EditText title;
    private EditText description;

    String artTitle,artDescription;

    //STEP 1 : opening Gallery
    private ImageView addimage;
    private final int REQ=1;
    //STEP 2 : storing the image in form of bitmap...
    private Bitmap bitmap;
    //show image in frame card view
    private ImageView showimage;
    //Button
    private Button upload;
    //    imguri
    private String imgurl="";

    EditText content;

    //FireBase RealTime Database
    //Storage Reference
    FirebaseStorage imgstorage;
    StorageReference imgstorageReference = imgstorage.getInstance().getReference();

    //Database Reference
    FirebaseDatabase imgdata;
    DatabaseReference imgdata_reference = imgdata.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_art);

        content = findViewById(R.id.description);
        content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (view.getId()==R.id.description)
                {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_UP: view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }


                return false;
            }
        });

        addimage = findViewById(R.id.addimage);
        showimage = findViewById(R.id.showimage);
        upload = findViewById(R.id.upload);
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageArt();
            }
        });
    }
//
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ && resultCode == RESULT_OK){
            Uri img_resource = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),img_resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            showimage.setImageBitmap(bitmap);
        }

    }

    public void openGallery(){
        Intent pickimage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickimage,REQ);
    }

    private void uploadImageArt() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] finalimg = baos.toByteArray();  //array created to store the image in java
        final StorageReference imgpath;
        imgpath = imgstorageReference.child("Art").child(finalimg + "jpg");
        final UploadTask uploadtask = imgpath.putBytes(finalimg);
        uploadtask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadtask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imgpath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imgurl = String.valueOf(uri);
                                    uploadArtData();
                                }
                            });
                        }
                    });
                } else {

                    Toast.makeText(add_art.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }//---------uploadimage FE function ends

    private void uploadArtData() {
        imgdata_reference = imgdata_reference.child("Art");
        final String uniqueKey = imgdata_reference.push().getKey();
        artTitle = title.getText().toString();
        artDescription = description.getText().toString();


        //        admin created Notice class
//        includes constructors
//        parameter to pass -> 1. title   2.imageUrl  3.date  4,time  5.key

        Eventsgetset eventsgetset = new Eventsgetset(artTitle,artDescription,imgurl, uniqueKey);
        imgdata_reference.child(uniqueKey).setValue(eventsgetset).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(add_art.this, "Art Uploaded", Toast.LENGTH_SHORT).show();

                title.setText("");
                description.setText("");



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(add_art.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

/*       uploadtask.addOnCompleteListener(add_art.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    uploadtask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imgpath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imgurl = String.valueOf(uri);
                                    uploadArtData();
                                }
                            });
                        }
                    });
                } else {

                    Toast.makeText(add_art.this, "Something goes Wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });
        */