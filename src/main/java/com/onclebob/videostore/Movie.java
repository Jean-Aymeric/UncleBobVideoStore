package com.onclebob.videostore;

public abstract class Movie {
    private final String title;

    public Movie(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public abstract int determineFrequentRenterPoints(int daysRented);

    public abstract double determineAmount(int daysRented);
}