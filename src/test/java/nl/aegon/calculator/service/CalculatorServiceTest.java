package nl.aegon.calculator.service;

import nl.aegon.calculator.exception.CalculatorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    public void testCanAddNumbers() {
        // given
        final int a = 10;
        final int b = 20;

        // when
        final double result = calculatorService.add(a, b);
        final double resultOverflow = calculatorService.add(a, Integer.MAX_VALUE);

        // then
        assertEquals(30, result);
        assertEquals((double)Integer.MAX_VALUE + a, resultOverflow);
    }

    @Test
    public void testCanSubtractNumbers() {
        // given
        final int a = 10;
        final int b = 20;

        // when
        final double result = calculatorService.subtract(a, b);
        final double resultOverflow = calculatorService.subtract(a, Integer.MAX_VALUE);

        // then
        assertEquals(-10, result);
        assertEquals((double)a - Integer.MAX_VALUE, resultOverflow);
    }

    @Test
    public void testCanMultiplyNumbers() {
        // given
        final int a = 10;
        final int b = 50;

        // when
        final double result = calculatorService.multiply(a, b);
        final double resultOverflow = calculatorService.multiply(a, Integer.MAX_VALUE);

        // then
        assertEquals(500, result);
        assertEquals((double)a * Integer.MAX_VALUE, resultOverflow);
    }

    @Test
    public void testCanDivideNumbers() {
        // given
        final int a = 10;
        final int b = 5;

        // when
        final double result = calculatorService.divide(a, b);
        final double resultZeroDivision = calculatorService.divide(0, 20);
        final double resultOverflow = calculatorService.divide(a, Integer.MAX_VALUE);

        // then
        assertEquals(2,  result);
        assertEquals(0, resultZeroDivision);
        assertEquals((double)a / Integer.MAX_VALUE, resultOverflow);
    }

    @Test
    public void testCannotDivideByZero() {
        // given
        final int a = 10;
        final int b = 0;

        // when, then
        assertThrows(CalculatorException.class, () -> calculatorService.divide(a, b));
    }
}
