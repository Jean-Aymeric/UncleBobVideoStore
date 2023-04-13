package com.onclebob.videostore;

public abstract class Movie {
    private String title;
    protected int priceCode;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract int determineFrequentRenterPoints(int daysRented);

    public abstract double determineAmount(int daysRented);
}