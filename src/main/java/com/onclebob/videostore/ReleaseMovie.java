package com.onclebob.videostore;

public class ReleaseMovie extends Movie {
    public ReleaseMovie(String title) {
        super(title);
    }

    @Override
    public double determineAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int determineFrequentRenterPoints(int daysRented) {
        boolean bonusIsEarned = daysRented > 1;
        return 1 + (bonusIsEarned ? 1 : 0);
    }
}
