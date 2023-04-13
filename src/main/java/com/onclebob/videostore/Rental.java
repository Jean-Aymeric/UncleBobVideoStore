package com.onclebob.videostore;

public class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(final Movie movie, final int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    double determineAmount() {
        return this.movie.determineAmount(this.daysRented);
    }

    public String getTitle() {
        return this.movie.getTitle();
    }

    public int determineFrequentRenterPoints() {
        return this.movie.determineFrequentRenterPoints(this.daysRented);
    }

}