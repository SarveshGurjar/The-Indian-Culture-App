package com.example.agencyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class art_2 extends AppCompatActivity {
    Arts_Adapter aAdapter;
    private RecyclerView artsrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art2);

        artsrecyclerview = findViewById(R.id.artsrecyclerview);

        //        to reverse the recycler view and show the latest post on top
        LinearLayoutManager LayoutMangager = new LinearLayoutManager(this);
        LayoutMangager.setReverseLayout(true);
        LayoutMangager.setStackFromEnd(true);
        artsrecyclerview.setLayoutManager(LayoutMangager);

        FirebaseRecyclerOptions<artsgetset> options =
                new FirebaseRecyclerOptions.Builder<artsgetset>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ART"), artsgetset.class)
                        .build();

        aAdapter = new Arts_Adapter(options);
        artsrecyclerview.setAdapter(aAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        aAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        aAdapter.stopListening();
    }
}