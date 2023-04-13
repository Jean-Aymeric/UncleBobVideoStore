package com.onclebob.videostore;

import java.text.MessageFormat;
import java.util.Vector;
import java.util.Enumeration;

public class Statement {
    private String customerName;
    private Vector rentals = new Vector();
    private double totalAmount;
    private int frequentRenterPoints;

    public Statement(String customerName) {
        this.customerName = customerName;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String generate() {
        clearTotals();
        String statementText = createHeader();
        statementText += createRentalLines();
        statementText += createFooter();
        return statementText;
    }

    private void clearTotals() {
        totalAmount = 0;
        frequentRenterPoints = 0;
    }

    private String createHeader() {
        return MessageFormat.format("com.onclebob.videostore.Rental Record for {0}\n", customerName);
    }

    private String createRentalLines() {
        String rentalLinesText = "";
        Enumeration rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental rental = (Rental) rentals.nextElement();
            rentalLinesText += createRentalLine(rental);
        }
        return rentalLinesText;
    }

    private String createRentalLine(Rental rental) {
        String rentalLineText = "";
        double rentalAmount = 0;

        rentalAmount = determineAmount(rental);
        rentalLineText = "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rentalAmount) + "\n";
        determineFrequentRenterPoints(rental);
        totalAmount += rentalAmount;
        return rentalLineText;
    }

    private static double determineAmount(Rental rental) {
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                return 2 + ((rental.getDaysRented() > 2) ? (rental.getDaysRented() - 2) * 1.5 : 0);
            case Movie.NEW_RELEASE:
                return rental.getDaysRented() * 3;
            case Movie.CHILDRENS:
                return 1.5 + ((rental.getDaysRented() > 3) ? (rental.getDaysRented() - 3) * 1.5 : 0);
        }
        return 0;
    }

    private void determineFrequentRenterPoints(Rental rental) {
        frequentRenterPoints++;
        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
                && rental.getDaysRented() > 1) {
            frequentRenterPoints++;
        }
    }

    private String createFooter() {
        return MessageFormat.format("""
                        You owed {0}
                        You earned {1} frequent renter points
                        """,
                String.valueOf(totalAmount),
                String.valueOf(frequentRenterPoints));
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }
}