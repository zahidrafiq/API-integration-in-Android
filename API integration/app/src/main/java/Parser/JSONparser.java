package Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import Model.Movie;
import Model.ResponseBO;

public class JSONparser {
    public ResponseBO parseServerData(String response){

        if(response != null){
            try {
                JSONObject jsonObj = new JSONObject(response);

                // Getting JSON Array node
                JSONArray movies = jsonObj.getJSONArray("results");

                // Save Data
                ArrayList<Movie> movieList = new ArrayList<>();
                // looping through All Contacts
                for (int i = 0; i < movies.length(); i++) {
                    JSONObject movie = movies.getJSONObject(i);

                    String t = movie.getString("title");
                    String overview = movie.getString("overview");
                    String posterPath=movie.getString("poster_path");
                    int voteAvg = movie.getInt("vote_average");;
                    String releaseDate = movie.getString("release_date");;
                    int id = movie.getInt("id");;


                    Movie m = new Movie();
                    m.setId(id);
                    m.setTitle(t);
                    m.setOverview(overview);
                    m.setReleaseDate(releaseDate);
                    m.setPosterPath(posterPath);
                    m.setVoteAvg(voteAvg);
                    movieList.add(m);

                }

                ResponseBO responseBO=new ResponseBO();
                responseBO.setMovieArrayList(movieList);
                return responseBO;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;

    }

}
