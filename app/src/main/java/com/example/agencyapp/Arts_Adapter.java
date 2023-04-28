package com.example.agencyapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;

public class Arts_Adapter extends FirebaseRecyclerAdapter<artsgetset,Arts_Adapter.myViewHolder> {

    public Arts_Adapter(@Nullable FirebaseRecyclerOptions<artsgetset> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Arts_Adapter.myViewHolder holder, int position, @NonNull artsgetset model) {
        holder.Title.setText(model.getTitle());
        holder.Description.setText((model.getDescription()));



        //Read Image
        Picasso.get()
                .load(model.getImage())
                .into(holder.img);
    }

    @NonNull
    @Override
    public Arts_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.art_layout,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView Title , Description;


        public myViewHolder(@NonNull View itemView){
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.artbanner);
            Title = (TextView)itemView.findViewById(R.id.arttitle);
            Description = (TextView)itemView.findViewById(R.id.artdescription);


        }

    }
}
