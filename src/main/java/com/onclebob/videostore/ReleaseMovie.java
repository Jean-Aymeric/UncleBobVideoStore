package com.onclebob.videostore;

public class ReleaseMovie extends Movie {
    public ReleaseMovie(final String title) {
        super(title);
    }

    @Override
    public double determineAmount(final int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int determineFrequentRenterPoints(final int daysRented) {
        final boolean bonusIsEarned = daysRented > 1;
        return 1 + (bonusIsEarned ? 1 : 0);
    }
}
