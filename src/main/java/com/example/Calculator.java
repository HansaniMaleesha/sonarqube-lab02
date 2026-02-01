package com.example;

public class Calculator {

    public int calculate(int a, int b, String op) {
        return switch (op) {
            case "add", "add-again" -> add(a, b);
            case "sub", "sub-again" -> a - b;
            case "mul" -> a * b;
            case "div" -> {
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                yield a / b;
            }
            case "mod" -> a % b;
            case "pow" -> power(a, b);
            default -> throw new UnsupportedOperationException(
                    "Unknown operation: " + op);
        };
    }

    private int add(int a, int b) {
        return a + b;
    }

    private int power(int a, int b) {
        int result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }
}
