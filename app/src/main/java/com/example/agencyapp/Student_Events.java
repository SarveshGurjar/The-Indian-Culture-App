package com.example.agencyapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Student_Events extends Fragment {
    private RecyclerView eventsrecyclerview;
    private ProgressBar progress;
    private ArrayList<Eventsgetset> list;
    private EventsAdapter adapter;
    EventsAdapter EA;

    //database
    private DatabaseReference reference;


    public Student_Events() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//       View view = inflater.inflate(R.layout.fragment_student__events, container, false);
//       eventsrecyclerview = view.findViewById(R.id.eventsrecyclerview);

        //        to reverse the recycler view and show the latest post on top
        LinearLayoutManager LayoutMangager = new LinearLayoutManager(getActivity());
        LayoutMangager.setReverseLayout(true);
        LayoutMangager.setStackFromEnd(true);
        eventsrecyclerview.setLayoutManager(LayoutMangager);

//       eventsrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


            FirebaseRecyclerOptions<Eventsgetset> options =
                    new FirebaseRecyclerOptions.Builder<Eventsgetset>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Events").child("SE"), Eventsgetset.class)
                            .build();

            EA = new EventsAdapter(options);
            eventsrecyclerview.setAdapter(EA);

       return null;
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