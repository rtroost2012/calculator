package nl.aegon.calculator.service;

import nl.aegon.calculator.exception.CalculatorException;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    /**
     * Addition of two numbers
     * @param a The first number
     * @param b The second number
     * @return The addition result
     */
    public double add(int a, int b) {
        return (double)a+b;
    }

    /**
     * Subtraction of two numbers
     * @param a The first number
     * @param b The second number
     * @return The subtraction result
     */
    public double subtract(int a, int b) {
        return (double)a-b;
    }

    /**
     * Multiplication of two numbers
     * @param a The first number
     * @param b The second number
     * @return The multiplication result
     */
    public double multiply(int a, int b) {
        return (double)a*b;
    }

    /**
     * Division of two numbers
     * @param a The first number
     * @param b The second number
     * @return The division result
     * @throws CalculatorException Division by zero Exception
     */
    public double divide(int a, int b) throws CalculatorException {
        if (b == 0) {
            throw new CalculatorException("Cannot divide a number by zero");
        }
        return (double)a/b;
    }
}
