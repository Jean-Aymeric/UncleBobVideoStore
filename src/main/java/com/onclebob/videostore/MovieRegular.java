package com.onclebob.videostore;

public class MovieRegular extends Movie {
    public MovieRegular(String title) {
        super(title);
    }

    @Override
    public double determineAmount(int daysRented) {
        return 2 + ((daysRented > 2) ? (daysRented - 2) * 1.5 : 0);
    }

    @Override
    public int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
