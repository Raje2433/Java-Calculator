public class CalculatorTest {
    public static void main(String[] args) {
        // Test Addition
        System.out.println("Addition Tests:");
        System.out.println("5 + 3 = " + Calculator.formatResult(Calculator.addition(5, 3)));   // Expected 8
        System.out.println("-5 + 10 = " + Calculator.formatResult(Calculator.addition(-5, 10))); // Expected 5
        System.out.println();

        // Test Subtraction
        System.out.println("Subtraction Tests:");
        System.out.println("10 - 5 = " + Calculator.formatResult(Calculator.subtraction(10, 5))); // Expected 5
        System.out.println("5 - 10 = " + Calculator.formatResult(Calculator.subtraction(5, 10))); // Expected -5
        System.out.println();

        // Test Multiplication
        System.out.println("Multiplication Tests:");
        System.out.println("4 * 3 = " + Calculator.formatResult(Calculator.multiplication(4, 3))); // Expected 12
        System.out.println("-2 * 6 = " + Calculator.formatResult(Calculator.multiplication(-2, 6))); // Expected -12
        System.out.println();

        // Test Division
        System.out.println("Division Tests:");
        System.out.println("10 / 2 = " + Calculator.formatResult(Calculator.division(10, 2))); // Expected 5
        System.out.println("7 / 2 = " + Calculator.formatResult(Calculator.division(7, 2)));   // Expected 3.50
        try {
            System.out.println("5 / 0 = " + Calculator.formatResult(Calculator.division(5, 0))); // Should fail
        } catch (ArithmeticException e) {
            System.out.println("Division by zero correctly handled!");
        }
        System.out.println();

        // Test Modulo
        System.out.println("Modulo Tests:");
        System.out.println("10 % 3 = " + Calculator.formatResult(Calculator.modulo(10, 3))); // Expected 1
        System.out.println("20 % 6 = " + Calculator.formatResult(Calculator.modulo(20, 6))); // Expected 2
        try {
            System.out.println("5 % 0 = " + Calculator.formatResult(Calculator.modulo(5, 0))); // Should fail
        } catch (ArithmeticException e) {
            System.out.println("Modulo by zero correctly handled!");
        }

        System.out.println("\n All manual tests executed using Calculator functions!");
    }
}
