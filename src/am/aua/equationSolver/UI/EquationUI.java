package am.aua.equationSolver.UI;

import am.aua.equationSolver.Equations.Equation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquationUI extends JFrame {
     static final Color BACKGROUND = new Color(101, 200, 100);
     static final Color BUTTON_COLOR = new Color(255, 255, 153);
    public EquationUI(){
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Equation Solver");
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel instructionLabel = new JLabel("Please choose an equation type:");
        instructionLabel.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Times New Roman font
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(instructionLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBackground(BACKGROUND);
        panel.add(buttonPanel, BorderLayout.CENTER);

        JButton linearEquationButton = new JButton("Linear Equation");
        linearEquationButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        linearEquationButton.setBackground(BUTTON_COLOR);
        linearEquationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEquationInputWindow("Linear Equation");
            }
        });

        JButton quadraticEquationButton = new JButton("Quadratic Equation");
        quadraticEquationButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        quadraticEquationButton.setBackground(BUTTON_COLOR);
        quadraticEquationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEquationInputWindow("Quadratic Equation");
            }
        });

        JButton systemOfLinearEquationsButton = new JButton("System of Linear Equations");
        systemOfLinearEquationsButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        systemOfLinearEquationsButton.setBackground(BUTTON_COLOR);
        systemOfLinearEquationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEquationInputWindow("System of Linear Equations");
            }
        });

        JButton exponentialEquationButton = new JButton("Exponential Equation");
        exponentialEquationButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        exponentialEquationButton.setBackground(BUTTON_COLOR);
        exponentialEquationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEquationInputWindow("Exponential Equation");
            }
        });


        buttonPanel.add(linearEquationButton);
        buttonPanel.add(quadraticEquationButton);
        buttonPanel.add(systemOfLinearEquationsButton);
        buttonPanel.add(exponentialEquationButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        getContentPane().add(panel);
        setVisible(true);
    }

    private void openEquationInputWindow(String equation) {
        new EquationInputUI(equation);
        dispose();
    }

    public static void main(String[] args) {
        EquationUI e = new EquationUI();
        e.setVisible(true);
    }
}
