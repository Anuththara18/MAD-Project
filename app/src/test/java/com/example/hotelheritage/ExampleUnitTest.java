package com.example.hotelheritage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {

    private BookingDetailsActivity bookingDetailsActivity;

    @Before
    public void setUp() {
        bookingDetailsActivity = new BookingDetailsActivity();
    }

    // IT19114040 - Jayakody J. A. D. K. A
    // For Deluxe Ocean View
    @Test
    public void calcTotalAmount_isCorrect_1() {
        float result = bookingDetailsActivity.calcTotalAmount(11000f, 1f, 2f, 2f);
        assertEquals(44000.0, result, 0.001);
    }

    // IT19114040 - Jayakody J. A. D. K. A
    // For Family Suite with Sea View
    @Test
    public void calcTotalAmount_isCorrect_2() {
        float result = bookingDetailsActivity.calcTotalAmount(12000f, 2f, 2f, 3f);
        assertEquals(108000.0, result, 0.001);
    }

    // IT19114040 - Jayakody J. A. D. K. A
    // For Junior Suite Superior
    @Test
    public void calcTotalAmount_isCorrect_3() {
        float result = bookingDetailsActivity.calcTotalAmount(13000f, 2f, 4f, 1f);
        assertEquals(52000.0, result, 0.001);
    }

    // IT19114040 - Jayakody J. A. D. K. A
    // For Executive Suite
    @Test
    public void calcTotalAmount_isCorrect_4() {
        float result = bookingDetailsActivity.calcTotalAmount(14000f, 3f, 4f, 3f);
        assertEquals(210000.0, result, 0.001);
    }

    // IT19114040 - Jayakody J. A. D. K. A
    // For Heritage Suite
    @Test
    public void calcTotalAmount_isCorrect_5() {
        float result = bookingDetailsActivity.calcTotalAmount(15000f, 3f, 4f, 3f);
        assertEquals(225000.0, result, 0.001);
    }

}