package com.example.agencyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class art_2 extends AppCompatActivity {
    private RecyclerView eventsrecyclerview;
    private ArrayList<Eventsgetset> list;
    private EventsAdapter adapter;
    EventsAdapter EA;

    private DatabaseReference reference;

    Arts_Adapter aAdapter;
    private RecyclerView artsrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art2);

        eventsrecyclerview = findViewById(R.id.eventsrecyclerview);

        LinearLayoutManager LayoutMangager = new LinearLayoutManager(art_2.this);
        LayoutMangager.setReverseLayout(true);
        LayoutMangager.setStackFromEnd(true);
        eventsrecyclerview.setLayoutManager(LayoutMangager);


        FirebaseRecyclerOptions<Eventsgetset> options =
                new FirebaseRecyclerOptions.Builder<Eventsgetset>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Art"), Eventsgetset.class)
                        .build();

        EA = new EventsAdapter(options);
        eventsrecyclerview.setAdapter(EA);
    }


    @Override
    public void onStart() {
        super.onStart();
        EA.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        EA.stopListening();
    }
}