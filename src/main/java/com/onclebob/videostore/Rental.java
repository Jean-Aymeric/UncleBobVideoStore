package com.onclebob.videostore;

public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    double determineAmount() {
        return movie.determineAmount(daysRented);
    }

    public String getTitle() {
        return this.movie.getTitle();
    }

    public int determineFrequentRenterPoints() {
        return movie.determineFrequentRenterPoints(daysRented);
    }

}