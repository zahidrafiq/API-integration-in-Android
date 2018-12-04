package com.example.zahid.apiintegrationmcassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import Model.Movie;

public class MovieDetailActivity extends AppCompatActivity{

    TextView tvTitle;
    TextView tvOverView;
    TextView tvRDtate;
    ImageView imgView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        imgView = findViewById(R.id.image_view_movie_detail);
        tvTitle = findViewById(R.id.tv_title_movie_detail);
        tvOverView = findViewById(R.id.tv_overview_movie_detail);
        tvRDtate = findViewById(R.id.tv_date_movie_detail);
        Intent i=getIntent();
        Bundle b = i.getExtras();
        Movie movie = (Movie) b.getSerializable("movieObj");
        tvTitle.setText(movie.getTitle());
        tvOverView.setText(movie.getOverview());
        tvRDtate.setText(movie.getReleaseDate());
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
                .resize(600,700)
                .into(imgView);


       // Toast.makeText(getApplicationContext(), b.get("title").toString(), Toast.LENGTH_LONG ).show();
    }
}
