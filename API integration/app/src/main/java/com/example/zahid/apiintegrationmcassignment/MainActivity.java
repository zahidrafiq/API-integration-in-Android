package com.example.zahid.apiintegrationmcassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Model.Movie;
import Model.ResponseBO;
import Parser.JSONparser;

public class MainActivity extends AppCompatActivity {

    public String urlStr = "https://api.themoviedb.org/3/movie/popular?api_key=cd1c6180346bde7bab3b141ac5d3effb&language=en-US&page=1";
    private ArrayList<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetServerData(getApplicationContext()).execute();
    }


    private class GetServerData extends AsyncTask<String, String, ResponseBO> {

        private ProgressDialog pDialog;
        private final Context context;

        public GetServerData(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected ResponseBO doInBackground(String... strings) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String val = br.readLine();
               return new JSONparser().parseServerData(val);
            } catch (Exception e) {
                System.out.println("Exception is :" + e);
            }
            return null;
        }


        @Override
        protected void onPostExecute(ResponseBO responseBO) {
            super.onPostExecute(responseBO);
            if (pDialog.isShowing())
                pDialog.dismiss();

            if (responseBO != null && responseBO.getMovieArrayList() != null && responseBO.getMovieArrayList().size() > 0) {
                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();

                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                final ArrayList<Movie> movieList = responseBO.getMovieArrayList();
                mAdapter = new MoviesAdapter(movieList);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(mAdapter);


                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Movie m = movieList.get(position);
                        Intent i = new Intent(context , MovieDetailActivity.class);
                        i.putExtra("movieObj",(Serializable)m);
                        startActivityForResult(i, -1);
                        //Toast.makeText(getApplicationContext(), "item clicked" + m.getReleaseDate(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

            } else {
                Toast.makeText(getApplicationContext(), "Internet is not available", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(ResponseBO responseBO) {
            super.onCancelled(responseBO);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}