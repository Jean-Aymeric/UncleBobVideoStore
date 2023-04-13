package com.onclebob.videostore;

public class ChildrenMovie extends Movie {
    public ChildrenMovie(String title) {
        super(title);
    }

    @Override
    public double determineAmount(int daysRented) {
        return 1.5 + ((daysRented > 3) ? (daysRented - 3) * 1.5 : 0);
    }

    @Override
    public int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}