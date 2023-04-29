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

public class EventsAdapter extends FirebaseRecyclerAdapter<Eventsgetset,EventsAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EventsAdapter(@NonNull FirebaseRecyclerOptions<Eventsgetset> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Eventsgetset model) {
        //Read Text



            holder.Title.setText(model.getTitle());
            holder.Description.setText((model.getDescription()));



            //Read Image
            Picasso.get()
                    .load(model.getImage())
                    .into(holder.img);


        }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.art_layout,parent,false);

        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView Title , Description , RegLink ;


        public myViewHolder(@NonNull View itemView){
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.artbanner);
            Title = (TextView)itemView.findViewById(R.id.arttitle);
            Description = (TextView)itemView.findViewById(R.id.artdescription);


        }

    }

}
