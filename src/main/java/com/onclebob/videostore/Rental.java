package com.onclebob.videostore;

public class Rental {
    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    double determineAmount() {
        switch (movie.getPriceCode()) {
            case Movie.REGULAR:
                return 2 + ((daysRented > 2) ? (daysRented - 2) * 1.5 : 0);
            case Movie.NEW_RELEASE:
                return daysRented * 3;
            case Movie.CHILDRENS:
                return 1.5 + ((daysRented > 3) ? (daysRented - 3) * 1.5 : 0);
        }
        return 0;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    private Movie movie;
    private int daysRented;

    public String getTitle() {
        return this.movie.getTitle();
    }

    public int determineFrequentRenterPoints() {
        boolean bonusIsEarned = (getMovie().getPriceCode() == Movie.NEW_RELEASE)
                && (getDaysRented() > 1);
        return bonusIsEarned ? 2 : 1;
    }
}