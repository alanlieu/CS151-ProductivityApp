package loginsignup;

import javax.swing.*;
import java.awt.*;

public class UserProfilePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel usernameLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;

    public UserProfilePanel(String username, String password, String email, String firstName, String lastName) {
        // Create layout, label, button
    	GridBagConstraints gbc = new GridBagConstraints();
        JLabel userProfileLabel = new JLabel("User Profile");
        JButton logoutButton = new JButton("Logout");
    	
        // Modify labels
        userProfileLabel.setFont(new Font("Arial", Font.BOLD, 24));
        usernameLabel = new JLabel("Username: " + username);
        firstNameLabel = new JLabel("First Name: " + firstName);
        lastNameLabel = new JLabel("Last Name: " + lastName);
        emailLabel = new JLabel("Email: " + email);
        passwordLabel = new JLabel("Password: " + password); 

        // Modify button
		logoutButton.setFocusPainted(false);
		logoutButton.setBorderPainted(false);
		logoutButton.setBackground(Color.RED);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setFont(new Font("Arial", Font.PLAIN, 18));
		logoutButton.setPreferredSize(new Dimension(150, 50));
		
		// Modify position, add to panel
		setLayout(new GridBagLayout());
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.insets = new Insets(50, 0, 10, 0);
	    add(userProfileLabel, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(usernameLabel, gbc);
        gbc.gridy++;
        add(firstNameLabel, gbc);
        gbc.gridy++;
        add(lastNameLabel, gbc);
        gbc.gridy++;
        add(emailLabel, gbc);
        gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 0, 0);
        add(logoutButton, gbc);
        
        // Button hover effect
		logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				logoutButton.setBackground(new Color(204, 0, 0));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				logoutButton.setBackground(Color.RED);
			}
		});
        
		// Confirm logout, return to welcome page
		logoutButton.addActionListener(e -> {
		    int confirmed = JOptionPane.showConfirmDialog(this,
		        "Are you sure you want to log out?", "Confirm Logout",
		        JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);

		    if (confirmed == JOptionPane.YES_OPTION) {
		        WelcomePage welcomePage = (WelcomePage) SwingUtilities.getWindowAncestor(UserProfilePanel.this);
		        welcomePage.remove(UserProfilePanel.this);
		        welcomePage.add(welcomePage.getMainPanel());
		        welcomePage.revalidate();
		        welcomePage.repaint();
		    }
		});
		
    }
}
