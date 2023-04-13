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
        double rentalAmount = rental.determineAmount();
        frequentRenterPoints += rental.determineFrequentRenterPoints();
        totalAmount += rentalAmount;
        return formatRentalLines(rental, rentalAmount);
    }

    private static String formatRentalLines(Rental rental, double rentalAmount) {
        return MessageFormat.format("\t{0}\t{1}\n", rental.getTitle(), String.valueOf(rentalAmount));
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