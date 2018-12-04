package Model;

import java.util.ArrayList;

public class ResponseBO {
    private ArrayList<Movie> movieArrayList = new ArrayList<>();

    public ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }

    public void setMovieArrayList(ArrayList<Movie> movieArrayList) {
        this.movieArrayList = movieArrayList;
    }

}
