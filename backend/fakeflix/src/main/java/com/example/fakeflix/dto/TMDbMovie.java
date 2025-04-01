package com.example.fakeflix.dto;

public class TMDbMovie {
    private String title;
    private String overview;
    private Double vote_average;
    private String poster_path; // Nueva propiedad

    // Getters y setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public Double getVote_average() {
        return vote_average;
    }
    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
