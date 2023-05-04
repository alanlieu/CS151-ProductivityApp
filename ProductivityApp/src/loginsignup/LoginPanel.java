package loginsignup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import app.NavMenu;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public LoginPanel() {
		// Create panel, labels, buttons
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel welcomeLabel = new JLabel("Welcome back!");
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		JTextField usernameField = new JTextField(20);
		JPasswordField passwordField = new JPasswordField(20);
		JButton loginButton = new JButton("Login");
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
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setBorderPainted(false);
		loginButton.setBackground(Color.BLUE);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(buttonFont);
		loginButton.setPreferredSize(new Dimension(150, 50));
		backButton.setBackground(Color.GRAY);
		backButton.setForeground(Color.WHITE);
		backButton.setFont(buttonFont);
		backButton.setPreferredSize(new Dimension(150, 50));
		usernameField.setBackground(color);
		usernameField.setForeground(textColor);
		usernameField.setPreferredSize(d);
		usernameField.setFont(font);
		usernameField.setBorder(null);
		passwordField.setBackground(color);
		passwordField.setForeground(textColor);
		passwordField.setFont(font);
		passwordField.setPreferredSize(d);
		passwordField.setBorder(null);
		welcomeLabel.setFont(textFont.deriveFont(24f));
		welcomeLabel.setForeground(textColor);
		usernameLabel.setFont(textFont);
		usernameLabel.setForeground(textColor);
		passwordLabel.setFont(textFont);
		passwordLabel.setForeground(textColor);

		// Modify position, add to panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(50, 0, 10, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(welcomeLabel, gbc);
		gbc.gridy++;
		gbc.insets = new Insets(5, 10, 0, 10);
		gbc.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(usernameLabel, gbc);
		gbc.gridy++;
		mainPanel.add(usernameField, gbc);
		gbc.gridy++;
		mainPanel.add(passwordLabel, gbc);
		gbc.gridy++;
		mainPanel.add(passwordField, gbc);
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(loginButton, gbc);
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(backButton, gbc);
		add(mainPanel);

		// Button hover effect
		loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				loginButton.setBackground(new Color(0, 0, 153));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				loginButton.setBackground(Color.BLUE);
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

		loginButton.addActionListener(e -> {
			// Get user input
			username = usernameField.getText();
			password = new String(passwordField.getPassword());

			// Check empty fields
			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(LoginPanel.this, "At least one field has been left blank.", "Blank field",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Correct info
			if (login(username, password)) {
				JOptionPane.showMessageDialog(LoginPanel.this, "Login successful!");

				// Reset input fields
				usernameField.setText("");
				passwordField.setText("");

				//close current window
				Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
				window.dispose();

				//open NavMenu
				NavMenu navMenu = new NavMenu();
				navMenu.setVisible(true);


			} else {
				JOptionPane.showMessageDialog(LoginPanel.this, "Incorrect username or password.");
			}
		});

		// Return to welcome page
		backButton.addActionListener(e -> {
			WelcomePage welcomePage = (WelcomePage) SwingUtilities.getWindowAncestor(LoginPanel.this);
			welcomePage.remove(LoginPanel.this);
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

	// Log in
	private boolean login(String username, String password) {
		try (Scanner scanner = new Scanner(new File("accounts.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				if (parts[0].equals(username) && parts[1].equals(password)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		new LoginPanel();
	}
}
