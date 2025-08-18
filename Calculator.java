import java.util.InputMismatchException;
import java.util.Scanner;
public class Calculator{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char option='Y';
        do{
        try{
        System.out.print("Enter the first number : ");
        double firstNumber = scan.nextDouble();
        System.out.print("Enter the second number : ");
        double secondNumber = scan.nextDouble();
        System.out.println("Enter the operand \n + for Addition \n - for Subtraction \n * for Multiplication\n / for Division \n % for Modulo ");
        char operand = scan.next().charAt(0);
        String result = switch(operand){
            case '+' -> "Result : " + formatResult(addition(firstNumber, secondNumber)); 
            case '-' -> "Result : " + formatResult(subtraction(firstNumber, secondNumber)); 
            case '*' -> "Result : " + formatResult(multiplication(firstNumber,secondNumber));
            case '/' -> (secondNumber!=0)?"Result : " + formatResult(division(firstNumber, secondNumber)):"Division by zero is invalid";
            case '%' -> (secondNumber!=0)?"Result : " + formatResult(modulo(firstNumber, secondNumber)):"Modulo by zero is invalid";
            default  -> "Invalid Operand";
        };
        System.out.println(result);
    }catch(InputMismatchException e){
        System.out.println("Invalid input! Please enter a number.");
        scan.next(); 
        continue; 
    }
        option = getYesNo(scan);
    }while(option == 'Y' || option == 'y');
    scan.close();
    }
    public static String formatResult(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);  // integer
        } else {
            return String.format("%.2f", value);       // decimal
        }
    }
    public static double addition(double a,double b){
        return a+b;
    }
    public static double subtraction(double a,double b){
        return a-b;
    }
    public static double multiplication(double a,double b){
        return a*b;
    }
    public static double division(double a,double b){
        return a/b;
    }
    public static double modulo(double a,double b){
        return a%b;
    }
    private static char getYesNo(Scanner scan) {
        char choice;
        do {
            System.out.print("Enter 'Y' to continue or 'N' to exit: ");
            choice = scan.next().charAt(0);
            if (choice != 'Y' && choice != 'y' && choice != 'N' && choice != 'n') {
                System.out.println("Invalid choice! Please enter only 'Y' or 'N'.");
            }
        } while (choice != 'Y' && choice != 'y' && choice != 'N' && choice != 'n');
    return choice;
}

}