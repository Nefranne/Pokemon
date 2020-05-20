package com.example.td3.presentation.model;

import java.util.List;

public class RestPokemonResponse {
    private Integer count;
    private String next;
    private List<Pokemon> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Pokemon> getResults() {
        return results;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }
}
