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
        double thisAmount = 0;

        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2) {
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3) {
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                }
                break;
        }
        rentalLineText = "\t" + rental.getMovie().getTitle() + "\t"
                + String.valueOf(thisAmount) + "\n";

        frequentRenterPoints++;
        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
                && rental.getDaysRented() > 1) {
            frequentRenterPoints++;
        }

        totalAmount += thisAmount;
        return rentalLineText;
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