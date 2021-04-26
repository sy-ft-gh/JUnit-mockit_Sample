package com.example.calc;

public class Tax {
    private final static double TAX_RATE = 0.1;
    public static double calcTax(double money) {
        return  TAX_RATE * money;
    }
}
