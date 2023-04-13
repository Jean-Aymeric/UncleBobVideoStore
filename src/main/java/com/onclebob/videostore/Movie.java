package com.onclebob.videostore;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public void setPriceCode(int code) {
        priceCode = code;
    }

    public String getTitle() {
        return title;
    }

    double determineAmount(int daysRented) {
        switch (priceCode) {
            case REGULAR:
                return 2 + ((daysRented > 2) ? (daysRented - 2) * 1.5 : 0);
            case NEW_RELEASE:
                return daysRented * 3;
            case CHILDRENS:
                return 1.5 + ((daysRented > 3) ? (daysRented - 3) * 1.5 : 0);
        }
        return 0;
    }

    public int determineFrequentRenterPoints(int daysRented) {
        boolean bonusIsEarned = (priceCode == NEW_RELEASE) && (daysRented > 1);
        return bonusIsEarned ? 2 : 1;
    }
}