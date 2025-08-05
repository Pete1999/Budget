package budget.ui;

import javax.swing.*;
import java.awt.*;

public class UserInputDialog extends JDialog {
    private final JTextField idField;
    private final JTextField usernameField;
    private final JTextField ageField;
    private boolean approved = false;

    public UserInputDialog(Frame owner) {
        super(owner, "Insert New User", true);
        setSize(300, 200);
        setLocationRelativeTo(owner);

        // Create fields
        idField = new JTextField(10);
        usernameField = new JTextField(20);
        ageField = new JTextField(10);

        // Layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID field
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        // Username field
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Age field
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        panel.add(ageField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            if (validateInput()) {
                approved = true;
                dispose();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add panels to dialog
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private boolean validateInput() {
        try {
            if (idField.getText().trim().isEmpty() || 
                usernameField.getText().trim().isEmpty() || 
                ageField.getText().trim().isEmpty()) {
                showError("All fields are required");
                return false;
            }

            int id = Integer.parseInt(idField.getText().trim());
            int age = Integer.parseInt(ageField.getText().trim());

            if (id <= 0) {
                showError("ID must be a positive number");
                return false;
            }

            if (age <= 0) {
                showError("Age must be a positive number");
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            showError("ID and Age must be valid numbers");
            return false;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", 
            JOptionPane.ERROR_MESSAGE);
    }

    public Integer getId() {
        return approved ? Integer.parseInt(idField.getText().trim()) : null;
    }

    public String getUsername() {
        return approved ? usernameField.getText().trim() : null;
    }

    public Integer getAge() {
        return approved ? Integer.parseInt(ageField.getText().trim()) : null;
    }

    public boolean isApproved() {
        return approved;
    }
}