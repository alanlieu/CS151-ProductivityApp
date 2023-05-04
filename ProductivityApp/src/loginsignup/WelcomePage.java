package loginsignup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class WelcomePage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private LoginPanel loginPanel;
	private SignupPanel signupPanel;

	public WelcomePage() {
		Color backgroundColor = Color.decode("#50525e");
		Color textColor = Color.decode("#ebeae3");
		Font buttonFont = new Font("Arial", Font.PLAIN, 18);
		
		setTitle("TimeKeeper");
		setSize(800, 800);
		setResizable(false);

		// Create panels, buttons, and label
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(backgroundColor);
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel welcomeLabel = new JLabel("Welcome to TimeKeeper!");
		JButton signupButton = new JButton("Signup");
		JButton loginButton = new JButton("Login");

		// Add image
		ImageIcon imageIcon = new ImageIcon("images/image_150x150.png");
		JLabel imageLabel = new JLabel(imageIcon);

		// Modify buttons and labels
		welcomeLabel.setForeground(textColor);
		signupButton.setFocusPainted(false);
		loginButton.setFocusPainted(false);
		signupButton.setBorderPainted(false);
		loginButton.setBorderPainted(false);
		signupButton.setBackground(new Color(255, 102, 0));
		loginButton.setBackground(Color.BLUE);
		signupButton.setForeground(Color.WHITE);
		loginButton.setForeground(Color.WHITE);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
		signupButton.setFont(buttonFont);
		loginButton.setFont(buttonFont);
		signupButton.setPreferredSize(new Dimension(150, 50));
		loginButton.setPreferredSize(new Dimension(150, 50));
		imageLabel.setPreferredSize(new Dimension(200, 200));

		// Modify position, add to panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);
		mainPanel.add(welcomeLabel, gbc);
		gbc.gridy = 1;
		mainPanel.add(imageLabel, gbc);
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 0, 0);
		mainPanel.add(loginButton, gbc);
		gbc.gridy = 3;
		gbc.insets = new Insets(10, 0, 0, 0);
		mainPanel.add(signupButton, gbc);
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

		// Confirm close window
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?",
						"Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					dispose();
				} else {
					setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}
			}
		});

		loginPanel = new LoginPanel();
		signupPanel = new SignupPanel();

		// Redirect to page
		signupButton.addActionListener(e -> {
			remove(mainPanel);
			add(signupPanel);
			revalidate();
			repaint();
		});

		loginButton.addActionListener(e -> {
			remove(mainPanel);
			add(loginPanel);
			revalidate();
			repaint();
		});

		add(mainPanel);
		setVisible(true);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public static void main(String[] args) {
		new WelcomePage();
	}

}
