import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordStrengthChecker extends JFrame {
    private JTextField passwordField;
    private JButton checkButton;

    public PasswordStrengthChecker() {
        // Set up the frame
        setTitle("Password Strength Checker");
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        passwordField = new JTextField(20);
        checkButton = new JButton("Check Password Strength");

        // Set up the layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Password:"));
        panel.add(passwordField);
        panel.add(checkButton);

        add(panel);

        // Add action listener to the button
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkPasswordStrength();
            }
        });
    }

    private void checkPasswordStrength() {
        String password = passwordField.getText();
        String feedback = analyzePassword(password);
        
        // Show the result in a new dialog window
        JOptionPane.showMessageDialog(this, feedback, "Password Strength", JOptionPane.INFORMATION_MESSAGE);
    }

    private String analyzePassword(String password) {
        int lengthCriteria = password.length() >= 8 ? 1 : 0;
        int upperCaseCriteria = password.chars().anyMatch(Character::isUpperCase) ? 1 : 0;
        int lowerCaseCriteria = password.chars().anyMatch(Character::isLowerCase) ? 1 : 0;
        int digitCriteria = password.chars().anyMatch(Character::isDigit) ? 1 : 0;
        int specialCharCriteria = password.chars().anyMatch(ch -> "!@#$%^&*()-_=+[]{}|;:,.<>?/".indexOf(ch) >= 0) ? 1 : 0;

        int criteriaMet = lengthCriteria + upperCaseCriteria + lowerCaseCriteria + digitCriteria + specialCharCriteria;

        String strength;
        String emoji;

        switch (criteriaMet) {
            case 5:
                strength = "Very Strong";
                emoji = "💪";
                break;
            case 4:
                strength = "Strong";
                emoji = "😊";
                break;
            case 3:
                strength = "Medium";
                emoji = "😐";
                break;
            case 2:
                strength = "Weak";
                emoji = "😟";
                break;
            default:
                strength = "Very Weak";
                emoji = "😢";
        }

        return String.format("Strength: %s %s", strength, emoji);
    }

    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(() -> {
            PasswordStrengthChecker checker = new PasswordStrengthChecker();
            checker.setVisible(true);
        });
    }
}
