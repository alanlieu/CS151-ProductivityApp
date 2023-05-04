package loginsignup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SignupPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public SignupPanel() {
		// Create panel, labels, buttons
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel createAccountLabel = new JLabel("Create an account");
		JLabel firstNameLabel = new JLabel("First Name");
		JLabel lastNameLabel = new JLabel("Last Name");
		JLabel emailLabel = new JLabel("Email");
		JLabel passwordLabel = new JLabel("Password");
		JTextField firstNameField = new JTextField(20);
		JTextField lastNameField = new JTextField(20);
		JTextField emailField = new JTextField(20);
		JPasswordField passwordField = new JPasswordField(20);
		JButton signupButton = new JButton("Signup");
		JButton backButton = new JButton("Back");
		Font font = new Font("Arial", Font.PLAIN, 22);
		Font buttonFont = new Font("Arial", Font.PLAIN, 18);
		Font textFont = new Font("Arial", Font.BOLD, 18);
		Color backgroundColor = Color.decode("#50525e");
		Color textColor = Color.decode("#ebeae3");
		Color color = Color.decode("#808080");
		Dimension d = new Dimension(80, 30);

		mainPanel.setBackground(backgroundColor);

		// Modify buttons and labels
		signupButton.setFocusPainted(false);
		signupButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setBorderPainted(false);
		signupButton.setBackground(new Color(255, 102, 0));
		signupButton.setForeground(Color.WHITE);
		signupButton.setFont(buttonFont);
		signupButton.setPreferredSize(new Dimension(150, 50));
		backButton.setBackground(Color.GRAY);
		backButton.setForeground(Color.WHITE);
		backButton.setFont(buttonFont);
		backButton.setPreferredSize(new Dimension(150, 50));
		createAccountLabel.setFont(textFont.deriveFont(24f));
		createAccountLabel.setForeground(textColor);
		firstNameLabel.setFont(textFont);
		firstNameLabel.setForeground(textColor);
		lastNameLabel.setFont(textFont);
		lastNameLabel.setForeground(textColor);
		emailLabel.setFont(textFont);
		emailLabel.setForeground(textColor);
		passwordLabel.setFont(textFont);
		passwordLabel.setForeground(textColor);
		firstNameField.setBackground(color);
		firstNameField.setForeground(textColor);
		firstNameField.setFont(font);
		firstNameField.setPreferredSize(d);
		firstNameField.setBorder(null);
		lastNameField.setBackground(color);
		lastNameField.setForeground(textColor);
		lastNameField.setFont(font);
		lastNameField.setPreferredSize(d);
		lastNameField.setBorder(null);
		emailField.setBackground(color);
		emailField.setForeground(textColor);
		emailField.setFont(font);
		emailField.setPreferredSize(d);
		emailField.setBorder(null);
		passwordField.setBackground(color);
		passwordField.setForeground(textColor);
		passwordField.setFont(font);
		passwordField.setPreferredSize(d);
		passwordField.setBorder(null);

		// Modify position, add to panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 0, 10, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(createAccountLabel, gbc);
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(5, 10, 5, 10);
		gbc.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(firstNameLabel, gbc);
		gbc.gridy++;
		mainPanel.add(firstNameField, gbc);
		gbc.gridy++;
		mainPanel.add(lastNameLabel, gbc);
		gbc.gridy++;
		mainPanel.add(lastNameField, gbc);
		gbc.gridy++;
		mainPanel.add(emailLabel, gbc);
		gbc.gridy++;
		mainPanel.add(emailField, gbc);
		gbc.gridy++;
		mainPanel.add(passwordLabel, gbc);
		gbc.gridy++;
		mainPanel.add(passwordField, gbc);
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(signupButton, gbc);
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(backButton, gbc);
		add(mainPanel);

		// Button hover effect
		signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				signupButton.setBackground(new Color(200, 100, 0));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				signupButton.setBackground(new Color(255, 102, 0));
			}
		});

		backButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				backButton.setBackground(Color.DARK_GRAY);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				backButton.setBackground(Color.GRAY);
			}
		});

		signupButton.addActionListener(e -> {
			// Get user input
			firstName = firstNameField.getText();
			lastName = lastNameField.getText();
			email = emailField.getText();
			password = new String(passwordField.getPassword());

			// Check empty fields
			if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(SignupPanel.this, "At least one field has been left blank.",
						"Blank field", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Password requirements
			try {
				validatePassword(password);
			} catch (loginsignup.PasswordException pe) {
				JOptionPane.showMessageDialog(SignupPanel.this, pe.getMessage(), "Password does not meet requirements",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Generate username, create account, show confirmation message
			String username = generateUsername(firstName, lastName);
			StringBuilder messageBuilder = new StringBuilder();
			messageBuilder.append("Welcome ").append(firstName).append("! Your account has been created!\n\n")
					.append("Your username is: ").append(username);
			JOptionPane.showMessageDialog(SignupPanel.this, messageBuilder.toString(), "Account creation successful",
					JOptionPane.INFORMATION_MESSAGE);
			createAccount(username, password, email, firstName, lastName);

			// Reset input fields
			firstNameField.setText("");
			lastNameField.setText("");
			emailField.setText("");
			passwordField.setText("");
		});

		// Return to welcome page
		backButton.addActionListener(e -> {
			WelcomePage welcomePage = (WelcomePage) SwingUtilities.getWindowAncestor(SignupPanel.this);
			welcomePage.remove(SignupPanel.this);
			welcomePage.add(welcomePage.getMainPanel());
			welcomePage.revalidate();
			welcomePage.repaint();
		});

		// Center content
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		add(mainPanel);
		add(Box.createVerticalGlue());
	}

	// Check the password for the requirements
	private void validatePassword(String password) throws loginsignup.PasswordException {
		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		boolean hasSpecialChar = false;
		boolean hasNumber = false;

		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isUpperCase(c)) {
				hasUpperCase = true;
			} else if (Character.isLowerCase(c)) {
				hasLowerCase = true;
			} else if (Character.isDigit(c)) {
				hasNumber = true;
			} else {
				hasSpecialChar = true;
			}
		}
		if (password.length() < 8) {
			throw new loginsignup.Minimum8CharactersRequired();
		}
		if (!hasUpperCase) {
			throw new loginsignup.UpperCaseCharacterMissing();
		}
		if (!hasLowerCase) {
			throw new loginsignup.LowerCaseCharacterMissing();
		}
		if (!hasSpecialChar) {
			throw new loginsignup.SpecialCharacterMissing();
		}
		if (!hasNumber) {
			throw new loginsignup.NumberCharacterMissing();
		}
	}

	// Method to generate username from first letter (first name, last name) and random number
	private String generateUsername(String firstName, String lastName) {
		char firstLetter = firstName.charAt(0);
		char secondLetter = lastName.charAt(0);
		Random rand = new Random();
		int randomNumber = rand.nextInt(1000, 10000);
		return String.format("%s%s-%d", Character.toUpperCase(firstLetter), Character.toUpperCase(secondLetter),
				randomNumber);
	}

	// Store info
	private void createAccount(String username, String password, String email, String firstName, String lastName) {
		Account account = new Account(username, password, email, firstName, lastName);

		// Write info to file
		try (PrintWriter writer = new PrintWriter(new FileWriter("accounts.txt", true))) {
			writer.println(account.getUsername() + "," + account.getPassword() + ", " + account.getEmail() + ", "
					+ account.getFirstName() + ", " + account.getLastName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SignupPanel();
	}
}
