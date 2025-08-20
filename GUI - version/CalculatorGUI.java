import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField expressionDisplay;  // small line for full expression
    private JTextField mainDisplay;        // big line for current number/result
    private StringBuilder currentInput;
    private StringBuilder expression;
    private double firstNumber = 0;
    private String operator = "";

    public CalculatorGUI() {
        // Frame settings
        setTitle("Calculator");
        setSize(320, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        currentInput = new StringBuilder();
        expression = new StringBuilder();

        // Expression display (small font)
        expressionDisplay = new JTextField();
        expressionDisplay.setFont(new Font("Arial", Font.PLAIN, 16));
        expressionDisplay.setEditable(false);
        expressionDisplay.setHorizontalAlignment(JTextField.RIGHT);

        // Main display (big font)
        mainDisplay = new JTextField();
        mainDisplay.setFont(new Font("Arial", Font.BOLD, 28));
        mainDisplay.setEditable(false);
        mainDisplay.setHorizontalAlignment(JTextField.RIGHT);

        // Top panel for both displays
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(expressionDisplay);
        topPanel.add(mainDisplay);
        add(topPanel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", ".", "=", "/",
            "C", "D", "%", ""
        };

        for (String text : buttons) {
            if (text.isEmpty()) {
                panel.add(new JLabel());
            } else {
                JButton button = new JButton(text);
                button.setFont(new Font("Arial", Font.BOLD, 20));
                button.addActionListener(this);
                panel.add(button);
            }
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Numbers
        if (command.matches("[0-9]")) {
            currentInput.append(command);
            mainDisplay.setText(currentInput.toString());
            expression.append(command);
            expressionDisplay.setText(expression.toString());
        }
        // Decimal
        else if (command.equals(".")) {
            if (!currentInput.toString().contains(".")) {
                if (currentInput.length() == 0) {
                    currentInput.append("0");
                    expression.append("0");
                }
                currentInput.append(".");
                mainDisplay.setText(currentInput.toString());
                expression.append(".");
                expressionDisplay.setText(expression.toString());
            }
        }
        // Operators
        else if (command.matches("[+\\-*/%]")) {
            if (currentInput.length() > 0) {
                firstNumber = Double.parseDouble(currentInput.toString());
                operator = command;
                currentInput.setLength(0);
                mainDisplay.setText(operator);
                expression.append(" ").append(operator).append(" ");
                expressionDisplay.setText(expression.toString());
            }
        }
        // Equals
        else if (command.equals("=")) {
            if (operator.isEmpty() || currentInput.length() == 0) return;

            double secondNumber = Double.parseDouble(currentInput.toString());
            double result = 0;

            switch (operator) {
                case "+" -> result = firstNumber + secondNumber;
                case "-" -> result = firstNumber - secondNumber;
                case "*" -> result = firstNumber * secondNumber;
                case "/" -> {
                    if (secondNumber == 0) {
                        mainDisplay.setText("Error: Division by zero");
                        reset();
                        return;
                    }
                    result = firstNumber / secondNumber;
                }
                case "%" -> {
                    if (secondNumber == 0) {
                        mainDisplay.setText("Error: Modulo by zero");
                        reset();
                        return;
                    }
                    result = firstNumber % secondNumber;
                }
            }

            String resultText = formatResult(result);
            mainDisplay.setText(resultText);
            expression.append(" = ").append(resultText);
            expressionDisplay.setText(expression.toString());

            currentInput.setLength(0);
            currentInput.append(resultText);
            operator = "";
        }
        // Clear
        else if (command.equals("C")) {
            reset();
            expressionDisplay.setText("");
            mainDisplay.setText("");
        }
        // Delete
        else if (command.equals("D")) {
            if (currentInput.length() > 0) {
                currentInput.deleteCharAt(currentInput.length() - 1);
                mainDisplay.setText(currentInput.toString());
                expression.setLength(expression.length() - 1);
                expressionDisplay.setText(expression.toString());
            }
        }
    }

    private String formatResult(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        } else {
            return String.format("%.2f", value);
        }
    }

    private void reset() {
        currentInput.setLength(0);
        expression.setLength(0);
        firstNumber = 0;
        operator = "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}
