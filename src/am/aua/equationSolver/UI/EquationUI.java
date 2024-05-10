package am.aua.equationSolver.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import am.aua.equationSolver.Equations.*;

public class EquationUI extends JFrame {
     static final Color BACKGROUND = new Color(77, 184, 184);
     static final Color BUTTON_COLOR = new Color(242, 242, 242);

    public EquationUI() {
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Equation Solver");
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instructionLabel = new JLabel("Please choose an equation type:");
        instructionLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(instructionLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBackground(BACKGROUND);
        panel.add(buttonPanel, BorderLayout.CENTER);

        addButton("Linear Equation", buttonPanel);
        addButton("Quadratic Equation", buttonPanel);
        addButton("System of Linear Equations", buttonPanel);
        addButton("Exponential Equation", buttonPanel);

        getContentPane().add(panel);
        setVisible(true);
    }

    private void addButton(String buttonLabel, JPanel buttonPanel) {
        JButton button = new JButton(buttonLabel);
        button.setFont(new Font("Times New Roman", Font.BOLD, 14));
        button.setBackground(BUTTON_COLOR);
        button.addActionListener(e -> openEquationInputWindow(buttonLabel));
        buttonPanel.add(button);
    }

    private void openEquationInputWindow(String equationType) {
        EquationInputUI inputUI = new EquationInputUI(equationType, "System of Linear Equations".equals(equationType) );
        inputUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                handleEquation(inputUI.getEquation(), equationType);
            }
        });
        dispose();
    }

    private void handleEquation(String equation, String equationType) {
        if (equation != null && !equation.isEmpty()) {
            try {
                Equation eq = createEquation(equationType, equation);
                if (eq.isSolvable()) {
                    solveEquation(eq);
                } else {
                    JOptionPane.showMessageDialog(null, "This equation is not solvable.");
                }
            } catch (WrongInputException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            } finally {
                new EquationUI().setVisible(true);
            }
        }
    }

    private Equation createEquation(String equationType, String equation) throws WrongInputException {
        switch (equationType) {
            case "Linear Equation":
                return new LinearEquation(equation);
            case "Quadratic Equation":
                return new QuadraticEquation(equation);
            case "System of Linear Equations":
                return parseSLE(equation);
            case "Exponential Equation":
                return new ExponentialEquation(equation);
            default:
                throw new IllegalArgumentException("Unsupported equation type: " + equationType);
        }
    }

    private SLE parseSLE(String equationsInput) throws WrongInputException {
        SLE sle = new SLE();
        String[] equationLines = equationsInput.split("\n");
        for (String line : equationLines) {
            if (!line.trim().isEmpty()) {
                sle.addEquation(new LinearEquation(line.trim()));
            }
        }
        return sle;
    }
    private void solveEquation(Equation eq) {
        if (eq instanceof LinearEquation) {
            LinearEquation le = (LinearEquation) eq;
            Object[] options = {"Solve for x", "Solve for y"};
            int choice = JOptionPane.showOptionDialog(null, "Select the variable to solve for:", "Variable Selection",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice == JOptionPane.YES_OPTION) { // Solve for x
                String input = JOptionPane.showInputDialog("Enter the value for y:");
                try {
                    double y = Double.parseDouble(input);
                    double result = le.solveForX(y);
                    JOptionPane.showMessageDialog(null, "Solution for x: " + result);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number entered for y.");
                } catch (WrongInputException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } else if (choice == JOptionPane.NO_OPTION) {
                String input = JOptionPane.showInputDialog("Enter the value for x:");
                try {
                    double x = Double.parseDouble(input);
                    double result = le.solveForY(x);
                    JOptionPane.showMessageDialog(null, "Solution for y: " + result);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number entered for x.");
                } catch (WrongInputException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        } else if (eq instanceof QuadraticEquation) {
            QuadraticEquation qe = (QuadraticEquation) eq;
            double[] solutions = qe.solve();
            if (solutions.length == 1) {
                JOptionPane.showMessageDialog(null, "One real root: " + solutions[0]);
            } else if (solutions.length == 2) {
                JOptionPane.showMessageDialog(null, "Two real and distinct roots: " + solutions[0] + " and " + solutions[1]);
            } else {
                JOptionPane.showMessageDialog(null, "No real roots.");
            }
        }else if (eq instanceof SLE) {
            SLE sle = (SLE) eq;
            try {
                double[] solutions = sle.solve();
                StringBuilder result = new StringBuilder("Solutions:\n");
                result.append("x").append(": ").append(solutions[0]).append("\n");
                result.append("y").append(": ").append(solutions[1]).append("\n");
                JOptionPane.showMessageDialog(null, result.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No unique solution or error: " + e.getMessage());
            }
        } else if (eq instanceof ExponentialEquation) {
            ExponentialEquation expEq = (ExponentialEquation) eq;
            try {
                double solution = expEq.solve();
                JOptionPane.showMessageDialog(null, "Solution: " + solution);
            } catch (WrongInputException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
