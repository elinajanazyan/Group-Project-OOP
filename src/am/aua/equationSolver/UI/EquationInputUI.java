package am.aua.equationSolver.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static am.aua.equationSolver.UI.EquationUI.BACKGROUND;

public class EquationInputUI extends JFrame {
    private String equation;
    public EquationInputUI(String equationType) {
        setTitle("Equation Input - " + equationType);
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Please write your " + equationType.toLowerCase() + " equation:");
        label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 100)); // Set preferred size for scroll pane

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation = textArea.getText();
                dispose();
            }
        });

        submitButton.setFont(new Font("Times New Roman", Font.BOLD, 14));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel for the submit button
        buttonPanel.add(submitButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER); // Add scroll pane instead of text area directly
        panel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel at the bottom

        getContentPane().add(panel);
        setVisible(true);
    }
}
