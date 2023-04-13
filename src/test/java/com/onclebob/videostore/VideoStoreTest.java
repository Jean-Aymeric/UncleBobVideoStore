package com.onclebob.videostore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VideoStoreTest {

    private static final double DELTA = 1e-15;
    private Statement statement;
    private final Movie newReleaseMovie1 = new ReleaseMovie("New Release 1");
    private final Movie newReleaseMovie2 = new ReleaseMovie("New Release 2");
    private final Movie newChildrenMovie1 = new ChildrenMovie("New Children 1");;
    private final Movie newRegular1 = new MovieRegular("New Regular 1");
    private final Movie newRegular2 = new MovieRegular("New Regular 2");
    private final Movie newRegular3 = new MovieRegular("New Regular 3");

    @BeforeEach
    protected void setUp() {
        statement = new Statement("Customer");
    }

    @Test
    public void testTotalsSingleNewReleaseStatement() {
        statement.addRental(new Rental(newReleaseMovie1, 3));
        statement.generate();
        assertEquals(9.0, statement.getTotalAmount(), DELTA);
        assertEquals(2, statement.getFrequentRenterPoints());
    }

    @Test
    public void testTotalsDualNewReleaseStatement() {
        statement.addRental(new Rental(newReleaseMovie1, 3));
        statement.addRental(new Rental(newReleaseMovie2, 3));
        statement.generate();
        assertEquals(18.0, statement.getTotalAmount(), DELTA);
        assertEquals(4, statement.getFrequentRenterPoints());
    }

    @Test
    public void testTotalsSingleChildrensStatement() {
        statement.addRental(new Rental(newChildrenMovie1, 3));
        statement.generate();
        assertEquals(1.5, statement.getTotalAmount(), DELTA);
        assertEquals(1, statement.getFrequentRenterPoints());
    }

    @Test
    public void testTotalsMultipleRegularStatement() {
        statement.addRental(new Rental(newRegular1, 1));
        statement.addRental(new Rental(newRegular2, 2));
        statement.addRental(new Rental(newRegular3, 3));
        statement.generate();
        assertEquals(7.5, statement.getTotalAmount(), DELTA);
        assertEquals(3, statement.getFrequentRenterPoints());
    }

    @Test
    public void testFormatMultipleRegularStatement() {
        statement.addRental(new Rental(newRegular1, 1));
        statement.addRental(new Rental(newRegular2, 2));
        statement.addRental(new Rental(newRegular3, 3));
        assertEquals("""
                com.onclebob.videostore.Rental Record for Customer
                \tNew Regular 1\t2.0
                \tNew Regular 2\t2.0
                \tNew Regular 3\t3.5
                You owed 7.5
                You earned 3 frequent renter points
                """, statement.generate());
    }
}
