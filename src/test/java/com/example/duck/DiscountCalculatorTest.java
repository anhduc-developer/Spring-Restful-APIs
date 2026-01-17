package com.example.duck;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DiscountCalculatorTest {
    @Test
    public void testNoDiscount() {
        DiscountCalculator discountObject = new DiscountCalculator();
        double amount = discountObject.calculatorDiscount(200);
        assertEquals(20, amount);
    }

    @Test
    public void test10Discount() {
        DiscountCalculator discountObject = new DiscountCalculator();
        double amount = discountObject.calculatorDiscount(100);
        assertEquals(0, amount);
    }
}
