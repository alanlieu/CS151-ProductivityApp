package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import loginsignup.WelcomePage;

public class NavMenu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel menuPanel;
	private JButton toggleButton;
	private JPanel contentPanel;
	private DashboardPanel dashboardPanel;
	private TimerPanel timerPanel;
	private TodolistPanel todolistPanel;

	public NavMenu() {
		Color menuPanelColor = Color.decode("#2a2a2d");
		Color toolBarColor = Color.decode("#333333");
		Color toggleColor = Color.decode("#808080");
		Color backgroundColor = Color.decode("#50525e");
		Font font = new Font("Verdana", Font.BOLD, 14);
		EmptyBorder menuPanelPadding = new EmptyBorder(0, 0, 20, 0);
		Dimension buttonSize = new Dimension(250, 50);
		ImageIcon dashboardIcon = new ImageIcon("images/round_dashboard_white_24dp.png");
		ImageIcon timerIcon = new ImageIcon("images/round_timer_white_24dp.png");
		ImageIcon todolistIcon = new ImageIcon("images/round_format_list_bulleted_white_24dp.png");
		ImageIcon logoutIcon = new ImageIcon("images/round_logout_white_24dp.png");
		ImageIcon titleIcon = new ImageIcon("images/round_hourglass_empty_white_24dp.png");
		JButton dashboardButton = new JButton("Dashboard", dashboardIcon);
		JButton timerButton = new JButton("Timer", timerIcon);
		JButton todolistButton = new JButton("To-Do List", todolistIcon);
		JButton logoutButton = new JButton("Logout", logoutIcon);
		JButton title = new JButton("TimeKeeper", titleIcon);
		JToolBar toolBar = new JToolBar();
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
		menuPanel.setBackground(menuPanelColor);
		menuPanel.setPreferredSize(new Dimension(250, getHeight()));

		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setFocusPainted(false);
		title.setBorderPainted(false);
		title.setBackground(menuPanelColor);
		title.setForeground(Color.WHITE);
		title.setFont(font.deriveFont(24f));
		title.setPreferredSize(buttonSize);
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonSize.height));
		title.setEnabled(false);
		dashboardButton.setHorizontalAlignment(SwingConstants.LEFT);
		dashboardButton.setFocusPainted(false);
		dashboardButton.setBorderPainted(false);
		dashboardButton.setBackground(menuPanelColor);
		dashboardButton.setForeground(Color.WHITE);
		dashboardButton.setFont(font);
		dashboardButton.setPreferredSize(buttonSize);
		dashboardButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonSize.height));
		timerButton.setHorizontalAlignment(SwingConstants.LEFT);
		timerButton.setFocusPainted(false);
		timerButton.setBorderPainted(false);
		timerButton.setBackground(menuPanelColor);
		timerButton.setForeground(Color.WHITE);
		timerButton.setFont(font);
		timerButton.setPreferredSize(buttonSize);
		timerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonSize.height));
		todolistButton.setHorizontalAlignment(SwingConstants.LEFT);
		todolistButton.setFocusPainted(false);
		todolistButton.setBorderPainted(false);
		todolistButton.setBackground(menuPanelColor);
		todolistButton.setForeground(Color.WHITE);
		todolistButton.setFont(font);
		todolistButton.setPreferredSize(buttonSize);
		todolistButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonSize.height));
		logoutButton.setHorizontalAlignment(SwingConstants.LEFT);
		logoutButton.setFocusPainted(false);
		logoutButton.setBorderPainted(false);
		logoutButton.setBackground(menuPanelColor);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setFont(font);
		logoutButton.setPreferredSize(buttonSize);
		logoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonSize.height));

		toggleButton = new JButton("☰");
		toggleButton.setFocusPainted(false);
		toggleButton.setBorderPainted(false);
		toggleButton.addActionListener(this);
		toggleButton.setPreferredSize(new Dimension(50, 50));
		toggleButton.setMaximumSize(new Dimension(50, 50));
		toggleButton.setBackground(toggleColor);

		toolBar.setFloatable(false);
		toolBar.add(toggleButton);
		toolBar.setBackground(toolBarColor);
		toolBar.setBorderPainted(false);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(toolBar, BorderLayout.NORTH);
		getContentPane().add(menuPanel, BorderLayout.WEST);

		contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		dashboardPanel = new DashboardPanel();
		contentPanel.add(dashboardPanel, BorderLayout.CENTER);
		contentPanel.setBackground(backgroundColor);

		menuPanel.add(title);
		menuPanel.setBorder(menuPanelPadding);
		menuPanel.add(dashboardButton);
		menuPanel.add(timerButton);
		menuPanel.add(todolistButton);
		menuPanel.add(Box.createVerticalGlue());
		menuPanel.add(logoutButton);

		toggleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				toggleButton.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				toggleButton.setBackground(toggleColor);
			}
		});

		dashboardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(dashboardPanel, BorderLayout.CENTER);
				contentPanel.revalidate();
				contentPanel.repaint();			
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				dashboardButton.setBackground(Color.DARK_GRAY);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				dashboardButton.setBackground(menuPanelColor);
			}

		});

		timerPanel = new TimerPanel();
		timerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(timerPanel, BorderLayout.CENTER);
				contentPanel.revalidate();
				contentPanel.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				timerButton.setBackground(Color.DARK_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				timerButton.setBackground(menuPanelColor);
			}
		});

		todolistPanel = new TodolistPanel();
		todolistButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(todolistPanel, BorderLayout.CENTER);
				contentPanel.revalidate();
				contentPanel.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				todolistButton.setBackground(Color.DARK_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				todolistButton.setBackground(menuPanelColor);
			}
		});

		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				logoutButton.setBackground(new Color(204, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				logoutButton.setBackground(menuPanelColor);
			}
		});

		logoutButton.addActionListener(e -> {
			int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Confirm Logout",
					JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);

			if (confirmed == JOptionPane.YES_OPTION) {
				dispose();
				new WelcomePage();
			}
		});

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

		setSize(1280, 720);
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == toggleButton) {
			menuPanel.setVisible(!menuPanel.isVisible());
		}
	}

	public static void main(String[] args) {
		new NavMenu();
	}

}
