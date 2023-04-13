package com.onclebob.videostore;

import java.text.MessageFormat;
import java.util.Vector;
import java.util.Enumeration;

public class Statement {
    private final String customerName;
    private final Vector<Rental> rentals = new Vector<>();
    private double totalAmount;
    private int frequentRenterPoints;

    public Statement(final String customerName) {
        this.customerName = customerName;
    }

    public void addRental(final Rental rental) {
        this.rentals.addElement(rental);
    }

    public String generate() {
        this.clearTotals();
        String statementText = this.createHeader();
        statementText += this.createRentalLines();
        statementText += this.createFooter();
        return statementText;
    }

    private void clearTotals() {
        this.totalAmount = 0;
        this.frequentRenterPoints = 0;
    }

    @SuppressWarnings("SpellCheckingInspection")
    private String createHeader() {
        return MessageFormat.format("com.onclebob.videostore.Rental Record for {0}\n", this.customerName);
    }

    private String createRentalLines() {
        final StringBuilder rentalLinesText = new StringBuilder();
        final Enumeration<Rental> rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            final Rental rental = rentals.nextElement();
            rentalLinesText.append(this.createRentalLine(rental));
        }
        return rentalLinesText.toString();
    }

    private String createRentalLine(final Rental rental) {
        final double rentalAmount = rental.determineAmount();
        this.frequentRenterPoints += rental.determineFrequentRenterPoints();
        this.totalAmount += rentalAmount;
        return Statement.formatRentalLines(rental, rentalAmount);
    }

    private static String formatRentalLines(final Rental rental, final double rentalAmount) {
        return MessageFormat.format("\t{0}\t{1}\n", rental.getTitle(), String.valueOf(rentalAmount));
    }

    private String createFooter() {
        return MessageFormat.format("""
                        You owed {0}
                        You earned {1} frequent renter points
                        """,
                String.valueOf(this.totalAmount),
                String.valueOf(this.frequentRenterPoints));
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public int getFrequentRenterPoints() {
        return this.frequentRenterPoints;
    }
}