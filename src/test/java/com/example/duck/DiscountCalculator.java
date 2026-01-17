package com.example.duck;

public class DiscountCalculator {
    public double calculatorDiscount(double totalAmount) {
        if (totalAmount < 100) {
            return 0;
        } else if (totalAmount < 100) {
            return totalAmount * 0.1;
        } else {
            return totalAmount * 0.2;
        }
    }
}
