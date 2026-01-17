package com.example.duck;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DiscountCalculatorTest {
    @Test
    public void testNoDiscount() {
        DiscountCalculator discountObject = new DiscountCalculator();
        // < 100 => 0
        double total = discountObject.calculatorDiscount(50);
        assertEquals(0, total);
    }

    @Test
    public void test10Discount() {
        DiscountCalculator discountCalculator = new DiscountCalculator();
        double total = discountCalculator.calculatorDiscount(150);
        assertEquals(30, total);
    }

    @Test
    public void test20Discount() {
        DiscountCalculator discountObject = new DiscountCalculator();
        // < 100 => 0
        double total = discountObject.calculatorDiscount(600);
        assertEquals(150, total);
    }
}
