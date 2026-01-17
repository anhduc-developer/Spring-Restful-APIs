package com.example.duck;

public class DiscountCalculator {
    public double calculatorDiscount(long quantity) {
        if (quantity < 100) {
            return 0;
        } else if (quantity < 500) {
            return quantity * 0.1;
        } else {
            return quantity * 0.2;
        }
    }
}
