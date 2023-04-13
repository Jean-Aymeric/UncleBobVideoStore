package com.onclebob.videostore;

public class ChildrenMovie extends Movie {
    public ChildrenMovie(final String title) {
        super(title);
    }

    @Override
    public double determineAmount(final int daysRented) {
        return 1.5 + ((daysRented > 3) ? (daysRented - 3) * 1.5 : 0);
    }

    @Override
    public int determineFrequentRenterPoints(final int daysRented) {
        return 1;
    }
}