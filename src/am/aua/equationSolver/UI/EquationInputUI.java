package am.aua.equationSolver.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquationInputUI extends JFrame {
    private String equation;
    private JTextArea textArea;

    public EquationInputUI(String equationType, boolean isMultiLine) {
        setTitle("Equation Input - " + equationType);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Please write your " + equationType.toLowerCase() + (isMultiLine ? " equations:" : " equation:"));
        label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        if (isMultiLine) {
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
        }
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 150));

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation = textArea.getText();
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
        setVisible(true);
    }

    public String getEquation() {
        return equation;
    }
}
