package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerticalNavMenu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel menuPanel;
	private JButton toggleButton;
	private JPanel contentPanel;

	public VerticalNavMenu() {
		super("Vertical Navigation Menu");

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Create the menu panel
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.setBackground(Color.LIGHT_GRAY);

		// Add the menu items
		addItem("Dashboard", new DashboardPanel());
		addItem("Timer", new TimerPanel());
		addItem("To-Do List", new TodolistPanel());
		addItem("Log Out", new LogoutPanel());

		// Create the toggle button
		toggleButton = new JButton("☰");
		toggleButton.setFocusable(false);
		toggleButton.addActionListener(this);
		toggleButton.setPreferredSize(new Dimension(50, 50));
		toggleButton.setMaximumSize(new Dimension(50, 50));

		// Create a toolbar for the toggle button
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(toggleButton);

		// Add the menu panel and toolbar to the content pane
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(toolBar, BorderLayout.NORTH);
		getContentPane().add(menuPanel, BorderLayout.WEST);

		// Set the preferred width of the menu panel
		menuPanel.setPreferredSize(new Dimension(250, getHeight()));

		// Create and add the main content panel
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// Set the size and show the frame
		setSize(1280, 720);
		setVisible(true);
	}

	private void addItem(String text, JPanel panel) {
		JLabel label = new JLabel(text);
		label.setForeground(Color.WHITE);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Add a mouse listener to show the selected panel
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(panel, BorderLayout.CENTER);
				contentPanel.revalidate();
				contentPanel.repaint();
			}
		});

		menuPanel.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == toggleButton) {
			menuPanel.setVisible(!menuPanel.isVisible());
		}
	}

	public static void main(String[] args) {
		new VerticalNavMenu();
	}

	// HomePanel
	class DashboardPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public DashboardPanel() {
			setLayout(new BorderLayout());
			add(new JLabel("Dashboard Panel", SwingConstants.CENTER), BorderLayout.CENTER);
		}
	}

	// AboutPanel
	class TimerPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public TimerPanel() {
			setLayout(new BorderLayout());
			add(new JLabel("Timer Panel", SwingConstants.CENTER), BorderLayout.CENTER);
		}
	}

	// ContactPanel
	class TodolistPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public TodolistPanel() {
			setLayout(new BorderLayout());
			add(new JLabel("To-Do List Panel", SwingConstants.CENTER), BorderLayout.CENTER);
		}
	}

	// SettingsPanel
	class LogoutPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public LogoutPanel() {
			setLayout(new BorderLayout());
			add(new JLabel("Log Out Panel", SwingConstants.CENTER), BorderLayout.CENTER);
		}
	}

}