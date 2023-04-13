package com.onclebob.videostore;

public class MovieRegular extends Movie {
    public MovieRegular(final String title) {
        super(title);
    }

    @Override
    public double determineAmount(final int daysRented) {
        return 2 + ((daysRented > 2) ? (daysRented - 2) * 1.5 : 0);
    }
 
    @Override
    public int determineFrequentRenterPoints(final int daysRented) {
        return 1;
    }
}
