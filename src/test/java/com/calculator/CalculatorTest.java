package com.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    
    @Test
    void testAdd() {
        assertEquals(5.0, Calculator.add(2, 3));
        assertEquals(7.5, Calculator.add(3.5, 4));
        assertEquals(-1.0, Calculator.add(-3, 2));
        assertEquals(0.0, Calculator.add(-5, 5));
    }
    
    @Test
    void testSubtract() {
        assertEquals(1.0, Calculator.subtract(3, 2));
        assertEquals(-0.5, Calculator.subtract(3.5, 4));
        assertEquals(-5.0, Calculator.subtract(-3, 2));
        assertEquals(10.0, Calculator.subtract(5, -5));
    }
    
    @Test
    void testMultiply() {
        assertEquals(6.0, Calculator.multiply(2, 3));
        assertEquals(14.0, Calculator.multiply(3.5, 4));
        assertEquals(-6.0, Calculator.multiply(-3, 2));
        assertEquals(-25.0, Calculator.multiply(5, -5));
    }
    
    @Test
    void testDivide() {
        assertEquals(2.0, Calculator.divide(6, 3));
        assertEquals(0.875, Calculator.divide(3.5, 4));
        assertEquals(-1.5, Calculator.divide(-3, 2));
        assertEquals(-1.0, Calculator.divide(5, -5));
    }
    
    @Test
    void testDivideByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            Calculator.divide(5, 0);
        });
        assertEquals("0으로 나눌 수 없습니다.", exception.getMessage());
    }
    
    @Test
    void testCalculateWithAddition() {
        assertEquals(8.0, Calculator.calculate(5, "+", 3));
    }
    
    @Test
    void testCalculateWithSubtraction() {
        assertEquals(2.0, Calculator.calculate(5, "-", 3));
    }
    
    @Test
    void testCalculateWithMultiplication() {
        assertEquals(15.0, Calculator.calculate(5, "*", 3));
    }
    
    @Test
    void testCalculateWithDivision() {
        assertEquals(2.5, Calculator.calculate(5, "/", 2));
    }
    
    @Test
    void testCalculateWithInvalidOperator() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Calculator.calculate(5, "%", 3);
        });
        assertTrue(exception.getMessage().contains("지원하지 않는 연산자입니다"));
    }
    
    @Test
    void testEvaluateSimpleAddition() {
        assertEquals(8.0, Calculator.evaluate("5 + 3"));
    }
    
    @Test
    void testEvaluateWithDecimals() {
        assertEquals(8.5, Calculator.evaluate("5.5 + 3"));
        assertEquals(2.75, Calculator.evaluate("11 / 4"));
    }
    
    @Test
    void testEvaluateWithoutSpaces() {
        assertEquals(8.0, Calculator.evaluate("5+3"));
        assertEquals(15.0, Calculator.evaluate("5*3"));
    }
    
    @Test
    void testEvaluateWithNegativeNumbers() {
        assertEquals(-2.0, Calculator.evaluate("-5 + 3"));
        assertEquals(-8.0, Calculator.evaluate("-5 - 3"));
        assertEquals(-15.0, Calculator.evaluate("-5 * 3"));
    }
    
    @Test
    void testEvaluateInvalidFormat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Calculator.evaluate("5 + 3 + 2");
        });
        assertTrue(exception.getMessage().contains("잘못된 수식 형식입니다"));
    }
    
    @Test
    void testEvaluateEmptyOperator() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Calculator.evaluate("5 3");
        });
        assertTrue(exception.getMessage().contains("잘못된 수식 형식입니다"));
    }
}
