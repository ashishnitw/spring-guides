package com.ashishnitw.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testMultiply() {
        assertEquals(20, calculator.multiply(2, 10));
    }

    @Test
    public void testMultiplyNegatives() {
        assertEquals(20, calculator.multiply(-4, -5));
    }

    @Test
    public void testMultiplyWithZero() {
        assertEquals(0, calculator.multiply(-10, 0));
    }

    @Test
    public void testDivide() {
        assertEquals(3, calculator.divide(10, 3));
    }

    @Test
    public void testDivideByZero() {

    }
}
