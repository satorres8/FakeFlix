package com.example.fakeflix.dto;

import java.util.List;

public class TMDbResponse {
    private List<TMDbMovie> results;

    public List<TMDbMovie> getResults() {
        return results;
    }
    public void setResults(List<TMDbMovie> results) {
        this.results = results;
    }
}
