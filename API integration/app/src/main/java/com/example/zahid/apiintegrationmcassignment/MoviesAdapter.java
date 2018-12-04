package com.example.zahid.apiintegrationmcassignment;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Model.Movie;
import Model.MovieItem;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private ArrayList<Movie> Movielist;
    private Context context;

    public MoviesAdapter(ArrayList<Movie> movielist) {
        Movielist = movielist;
//        this.context = context;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int i) {
        Movie movie =  Movielist.get(i);
        holder.titleView.setText(movie.getTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
            .resize(150,200)
                .into(holder.imgView);
}

    @Override
    public int getItemCount() {
        return Movielist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleView;
        ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.tv_title);
            imgView = (ImageView) itemView.findViewById(R.id.imgView_image);
        }
    }
}