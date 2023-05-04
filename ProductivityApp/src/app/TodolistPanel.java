package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class TodolistPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JButton addButton;
	private JButton clearButton;
	private ArrayList<TaskRow> rows;
	private int timerIndex;

	public TodolistPanel() {
		Color addButtonColor = Color.decode("#00CC66");
		Color clearButtonColor = Color.decode("#808080");
		Color backgroundColor = Color.decode("#50525e");
		Dimension buttonSize = new Dimension(80, 30);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBackground(backgroundColor);

		JScrollPane scrollPane = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setBackground(Color.decode("#a3a59e"));
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = clearButtonColor;
		    }
		});
		
		rows = new ArrayList<>();
		timerIndex = -1;

		JPanel buttonPanel = new JPanel();
		addButton = new JButton("➕");
		addButton.addActionListener(this);
		buttonPanel.add(addButton);

		clearButton = new JButton("🗑");
		clearButton.addActionListener(this);
		buttonPanel.add(clearButton);
		buttonPanel.setBackground(backgroundColor);

		addButton.setPreferredSize(buttonSize);
		addButton.setBackground(addButtonColor);
		addButton.setFocusPainted(false);
		addButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		clearButton.setPreferredSize(buttonSize);
		clearButton.setBackground(clearButtonColor);
		clearButton.setFocusPainted(false);
		clearButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		JPanel backgroundPanel = new JPanel(new GridBagLayout());
		backgroundPanel.setBackground(backgroundColor);

		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.NORTH;
		backgroundPanel.add(buttonPanel, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		backgroundPanel.add(scrollPane, gbc);

		this.add(backgroundPanel);

		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				addButton.setBackground(Color.decode("#00FF80"));
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				addButton.setBackground(addButtonColor);
			}
		});

		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				clearButton.setBackground(Color.decode("#A0A0A0"));
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				clearButton.setBackground(clearButtonColor);
			}
		});

		backgroundPanel.setPreferredSize(new Dimension(1260, 620));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			TaskRow row = new TaskRow();
			rows.add(row);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = rows.size() - 1;
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.fill = GridBagConstraints.BOTH;
			gbc.weightx = 1;
			gbc.weighty = 0;
			mainPanel.add(row.panel, gbc);

			mainPanel.revalidate();
			mainPanel.repaint();
		} else if (e.getSource() == clearButton) {
			rows.clear();
			mainPanel.removeAll();
			mainPanel.revalidate();
			mainPanel.repaint();
		} else {
			int index = -1;
			for (int i = 0; i < rows.size(); i++) {
				if (e.getSource() == rows.get(i).removeButton) {
					index = i;
					break;
				}
			}
			if (index != -1) {
				TaskRow row = rows.remove(index);
				mainPanel.remove(row.panel);
				mainPanel.revalidate();
				mainPanel.repaint();

				if (index == timerIndex) {
					row.stopTimer();
					timerIndex = -1;
				}
			}
		}
	}

	public class TaskRow implements ActionListener {
		private JPanel panel;
		private JTextField nameField;
		private JSpinner hourSpinner;
		private JSpinner minuteSpinner;
		private JSpinner secondSpinner;
		private JLabel timerLabel;
		private JLabel hrLabel;
		private JLabel minLabel;
		private JLabel secLabel;
		private JButton startButton;
		private JCheckBox checkBox;
		private JButton removeButton;
		private Timer timer;
		private int remainingTime;
		private JButton pauseResumeButton;
		private boolean isPaused;

		public TaskRow() {
			Font font = new Font("Product Sans", Font.PLAIN, 22);
			Dimension d = new Dimension(80, 30);
			Dimension buttonSize = new Dimension(60, 30);
			Color startButtonColor = Color.decode("#00CC66");
			Color removeButtonColor = Color.decode("#FF3333");
			Color pauseResumeButtonColor = Color.decode("#CCCC00");
			Color backgroundColor = Color.decode("#50525e");
			Color color = Color.decode("#808080");
	        Border raisedEtched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	        Border loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			ImageIcon unchecked = new ImageIcon("images/outline_check_box_outline_blank_white_24dp.png");
			ImageIcon checked = new ImageIcon("images/outline_check_box_white_24dp.png");
			
			panel = new JPanel(new GridBagLayout());
			nameField = new JTextField(15);
			hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
			minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
			secondSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
			timerLabel = new JLabel("00:00:00");
			hrLabel = new JLabel("HR");
			minLabel = new JLabel("MIN");
			secLabel = new JLabel("SEC");
			startButton = new JButton("▶");
			startButton.addActionListener(this);
			checkBox = new JCheckBox();
			removeButton = new JButton("✖");
			removeButton.addActionListener(TodolistPanel.this);
			pauseResumeButton = new JButton("❙❙");
			pauseResumeButton.addActionListener(this);
			isPaused = false;

			panel.setBorder(BorderFactory.createCompoundBorder(raisedEtched, loweredEtched));
			panel.setBackground(backgroundColor);
			nameField.setBackground(color);
			nameField.setFont(font);
			nameField.setPreferredSize(d);
			nameField.setBorder(null);
			startButton.setPreferredSize(buttonSize);
			removeButton.setPreferredSize(buttonSize);
			pauseResumeButton.setPreferredSize(buttonSize);
			checkBox.setSize(new Dimension(50, 50));
			checkBox.setBackground(backgroundColor);
			checkBox.setIcon(unchecked);
			checkBox.setSelectedIcon(checked);
			hourSpinner.setPreferredSize(d);
			hourSpinner.setFont(font);
			hourSpinner.setBorder(null);
			hourSpinner.getEditor().getComponent(0).setBackground(color);
			minuteSpinner.setPreferredSize(d);
			minuteSpinner.setFont(font);
			minuteSpinner.setBorder(null);
			minuteSpinner.getEditor().getComponent(0).setBackground(color);
			secondSpinner.setPreferredSize(d);
			secondSpinner.setFont(font);
			secondSpinner.setBorder(null);
			secondSpinner.getEditor().getComponent(0).setBackground(color);
			timerLabel.setFont(font.deriveFont(28f));
			timerLabel.setBorder(null);
			timerLabel.setOpaque(true);
			timerLabel.setBackground(color);
			timerLabel.setForeground(Color.DARK_GRAY);
			hrLabel.setFont(font);
			hrLabel.setForeground(Color.WHITE);
			minLabel.setFont(font);
			minLabel.setForeground(Color.WHITE);
			secLabel.setFont(font);
			secLabel.setForeground(Color.WHITE);

			startButton.setBackground(startButtonColor);
			startButton.setFocusPainted(false);
			startButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			removeButton.setBackground(removeButtonColor);
			removeButton.setFocusPainted(false);
			removeButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pauseResumeButton.setBackground(pauseResumeButtonColor);
			pauseResumeButton.setFocusPainted(false);
			pauseResumeButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.anchor = GridBagConstraints.WEST;
			panel.add(checkBox, gbc);

			gbc.gridx++;
			gbc.anchor = GridBagConstraints.CENTER;
			panel.add(timerLabel, gbc);

			gbc.gridx++;
			gbc.anchor = GridBagConstraints.WEST;
			panel.add(nameField, gbc);

			gbc.gridx++;
			gbc.anchor = GridBagConstraints.CENTER;
			panel.add(hrLabel, gbc);

			gbc.gridx++;
			panel.add(hourSpinner, gbc);

			gbc.gridx++;
			gbc.anchor = GridBagConstraints.CENTER;
			panel.add(minLabel, gbc);

			gbc.gridx++;
			panel.add(minuteSpinner, gbc);

			gbc.gridx++;
			gbc.anchor = GridBagConstraints.CENTER;
			panel.add(secLabel, gbc);

			gbc.gridx++;
			panel.add(secondSpinner, gbc);

			gbc.gridx++;
			panel.add(startButton, gbc);

			gbc.gridx++;
			panel.add(pauseResumeButton, gbc);

			gbc.gridx++;
			panel.add(removeButton, gbc);

			timer = new Timer(1000, e -> {
				remainingTime--;
				if (remainingTime <= 0) {
					stopTimer();
					checkBox.setSelected(true);
				} else {
					updateTimerDisplay();
				}
			});

			startButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent evt) {
					startButton.setBackground(Color.decode("#00FF80"));
				}

				@Override
				public void mouseExited(MouseEvent evt) {
					startButton.setBackground(startButtonColor);
				}
			});

			removeButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent evt) {
					removeButton.setBackground(Color.decode("#FF6666"));
				}

				@Override
				public void mouseExited(MouseEvent evt) {
					removeButton.setBackground(removeButtonColor);
				}
			});

			pauseResumeButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent evt) {
					pauseResumeButton.setBackground(Color.decode("#FFFF66"));
				}

				@Override
				public void mouseExited(MouseEvent evt) {
					pauseResumeButton.setBackground(pauseResumeButtonColor);
				}
			});

			nameField.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					enableDisableStartButton();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					enableDisableStartButton();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					enableDisableStartButton();
				}
			});

			enableDisableStartButton();
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == startButton && timerIndex == -1) {
				remainingTime = (int) hourSpinner.getValue() * 3600 + (int) minuteSpinner.getValue() * 60
						+ (int) secondSpinner.getValue();
				if (remainingTime > 0) {
					timer.start();
					timerIndex = rows.indexOf(this);
					updateTimerDisplay();
				}
			} else if (e.getSource() == pauseResumeButton && timerIndex == rows.indexOf(this)) {
				if (isPaused) {
					timer.start();
					pauseResumeButton.setText("❙❙");
					isPaused = false;
				} else {
					timer.stop();
					pauseResumeButton.setText("❙❙▶");
					isPaused = true;
				}
			}
		}
		
		void enableDisableStartButton() {
			startButton.setEnabled(!nameField.getText().isEmpty());
		}

		void stopTimer() {
			timer.stop();
			remainingTime = 0;
			updateTimerDisplay();
			if (TodolistPanel.this.timerIndex == rows.indexOf(this)) {
				TodolistPanel.this.timerIndex = -1;
			}
		}

		void updateTimerDisplay() {
			int hours = remainingTime / 3600;
			int minutes = (remainingTime % 3600) / 60;
			int seconds = remainingTime % 60;
			String timerText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			timerLabel.setText(timerText);
		}
	}
}