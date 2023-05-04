package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class TimerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JSpinner workHourSpinner;
	private JSpinner workMinSpinner;
	private JSpinner workSecSpinner;
	private JSpinner restHourSpinner;
	private JSpinner restMinSpinner;
	private JSpinner restSecSpinner;
	private JLabel workTimerLabel;
	private JLabel restTimerLabel;
	private JButton startButton;
	private JButton pauseButton;
	private JButton resetButton;
	private Timer workTimer;
	private Timer restTimer;
	private int workRemainingSeconds;
	private int restRemainingSeconds;
	private static int workCount;
	private static int restCount;
	
	public TimerPanel() {
		Font font = new Font("Verdana", Font.PLAIN, 42);
		Font titleFont = new Font("Verdana", Font.BOLD, 32);
		Dimension buttonSize = new Dimension(80, 40);
		Dimension spinnerSize = new Dimension(80, 30);
		Color backgroundColor = Color.decode("#d2cfc8");
		Color color = Color.decode("#bfbebb");
		JLabel workHourLabel = new JLabel("Hours");
		JLabel workMinuteLabel = new JLabel("Minutes");
		JLabel workSecondLabel = new JLabel("Seconds");
		JLabel restHourLabel = new JLabel("Hours");
		JLabel restMinuteLabel = new JLabel("Minutes");
		JLabel restSecondLabel = new JLabel("Seconds");
		JPanel timerPanel = new JPanel();
		
		timerPanel.setBackground(backgroundColor);
		timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));
		timerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Timer",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, titleFont));
		add(timerPanel);

		workHourLabel.setFont(font.deriveFont(24f));
		workMinuteLabel.setFont(font.deriveFont(24f));
		workSecondLabel.setFont(font.deriveFont(24f));
		restHourLabel.setFont(font.deriveFont(24f));
		restMinuteLabel.setFont(font.deriveFont(24f));
		restSecondLabel.setFont(font.deriveFont(24f));

		timerPanel.add(Box.createVerticalStrut(30));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		workTimerLabel = new JLabel("00:00:00");
		workTimerLabel.setOpaque(true);
		workTimerLabel.setFont(font);
		JPanel workDisplayPanel = new JPanel(new GridBagLayout());
		workDisplayPanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
		workDisplayPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		workDisplayPanel.add(workTimerLabel, gbc);
		timerPanel.add(workDisplayPanel);

		JPanel workTimerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		workTimerPanel.setBackground(backgroundColor);
		workTimerPanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
		workTimerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Work Timer", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, titleFont));
		timerPanel.add(Box.createVerticalStrut(35));
		workHourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
		workHourSpinner.getEditor().getComponent(0).setBackground(color);
		workHourSpinner.setBorder(null);
		workTimerPanel.add(workHourSpinner);
		workTimerPanel.add(workHourLabel);
		workMinSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		workMinSpinner.getEditor().getComponent(0).setBackground(color);
		workMinSpinner.setBorder(null);
		workTimerPanel.add(workMinSpinner);
		workTimerPanel.add(workMinuteLabel);
		workSecSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		workSecSpinner.getEditor().getComponent(0).setBackground(color);
		workSecSpinner.setBorder(null);
		workTimerPanel.add(workSecSpinner);
		workTimerPanel.add(workSecondLabel);
		timerPanel.add(workTimerPanel);
		timerPanel.add(Box.createVerticalStrut(35));

		timerPanel.add(Box.createVerticalStrut(40));
		gbc.gridx++;
		restTimerLabel = new JLabel("00:00:00");
		restTimerLabel.setOpaque(true);
		restTimerLabel.setFont(font);
		JPanel restDisplayPanel = new JPanel(new GridBagLayout());
		restDisplayPanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
		restDisplayPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		restDisplayPanel.add(restTimerLabel, gbc);
		timerPanel.add(restDisplayPanel);

		JPanel restTimerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		restTimerPanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
		restTimerPanel.setBackground(backgroundColor);
		restTimerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Rest Timer", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, titleFont));
		timerPanel.add(Box.createVerticalStrut(35));
		restHourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
		restHourSpinner.getEditor().getComponent(0).setBackground(color);
		restHourSpinner.setBorder(null);
		restTimerPanel.add(restHourSpinner);
		restTimerPanel.add(restHourLabel);
		restMinSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		restMinSpinner.getEditor().getComponent(0).setBackground(color);
		restMinSpinner.setBorder(null);
		restTimerPanel.add(restMinSpinner);
		restTimerPanel.add(restMinuteLabel);
		restSecSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		restSecSpinner.getEditor().getComponent(0).setBackground(color);
		restSecSpinner.setBorder(null);
		restTimerPanel.add(restSecSpinner);
		restTimerPanel.add(restSecondLabel);
		timerPanel.add(restTimerPanel);
		timerPanel.add(Box.createVerticalStrut(50));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(backgroundColor);
		startButton = new JButton("▶");
		startButton.setPreferredSize(buttonSize);
		startButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		startButton.addActionListener(e -> startTimers());
		buttonPanel.add(startButton);

		pauseButton = new JButton("❙❙");
		pauseButton.setPreferredSize(buttonSize);
		pauseButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pauseButton.addActionListener(e -> pauseTimers());
		buttonPanel.add(pauseButton);

		resetButton = new JButton("✖");
		resetButton.setPreferredSize(buttonSize);
		resetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		resetButton.addActionListener(e -> resetTimers());
		buttonPanel.add(resetButton);

		timerPanel.add(buttonPanel);

		Color startButtonColor = Color.decode("#00CC66");
		Color pauseButtonColor = Color.decode("#CCCC00");
		Color resetButtonColor = Color.decode("#FF3333");

		startButton.setBackground(startButtonColor);
		startButton.setFocusPainted(false);
		pauseButton.setBackground(pauseButtonColor);
		pauseButton.setFocusPainted(false);
		resetButton.setBackground(resetButtonColor);
		resetButton.setFocusPainted(false);
		workHourSpinner.setPreferredSize(spinnerSize);
		workHourSpinner.setFont(font.deriveFont(24f));
		workMinSpinner.setPreferredSize(spinnerSize);
		workMinSpinner.setFont(font.deriveFont(24f));
		workSecSpinner.setPreferredSize(spinnerSize);
		workSecSpinner.setFont(font.deriveFont(24f));
		restHourSpinner.setPreferredSize(spinnerSize);
		restHourSpinner.setFont(font.deriveFont(24f));
		restMinSpinner.setPreferredSize(spinnerSize);
		restMinSpinner.setFont(font.deriveFont(24f));
		restSecSpinner.setPreferredSize(spinnerSize);
		restSecSpinner.setFont(font.deriveFont(24f));

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

		pauseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				pauseButton.setBackground(Color.decode("#FFFF00"));
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				pauseButton.setBackground(pauseButtonColor);
			}
		});

		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				resetButton.setBackground(Color.decode("#FF6666"));
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				resetButton.setBackground(resetButtonColor);
			}
		});
		
		workTimer = new Timer(1000, e -> updateWorkTimer());
		restTimer = new Timer(1000, e -> updateRestTimer());

		restRemainingSeconds = (int) restHourSpinner.getValue() * 3600 + (int) restMinSpinner.getValue() * 60
				+ (int) restSecSpinner.getValue();
		workRemainingSeconds = (int) workHourSpinner.getValue() * 3600 + (int) workMinSpinner.getValue() * 60
				+ (int) workSecSpinner.getValue();
		
	}
    
    void actionPerformed(ActionEvent event) {
    	if (event.getSource() == workTimer) {
    	    workCount++;
    	} else if (event.getSource() == restTimer) {
    	    restCount++;
    	}
    }
	
	void startTimers() {
		if ((int) workHourSpinner.getValue() == 0 && (int) workMinSpinner.getValue() == 0
				&& (int) workSecSpinner.getValue() == 0 && (int) restHourSpinner.getValue() == 0
				&& (int) restMinSpinner.getValue() == 0 && (int) restSecSpinner.getValue() == 0) {
			return;
		}

		if (workRemainingSeconds == 0 || restRemainingSeconds == 0) {
			startButton.setEnabled(false);
		}

		int workHours = (int) workHourSpinner.getValue();
		int workMinutes = (int) workMinSpinner.getValue();
		int workSeconds = (int) workSecSpinner.getValue();
		int restHours = (int) restHourSpinner.getValue();
		int restMinutes = (int) restMinSpinner.getValue();
		int restSeconds = (int) restSecSpinner.getValue();

		workRemainingSeconds = workHours * 3600 + workMinutes * 60 + workSeconds + 1;
		restRemainingSeconds = restHours * 3600 + restMinutes * 60 + restSeconds;

		workTimer.start();
	}

	void pauseTimers() {
		if ((int) workHourSpinner.getValue() == 0 && (int) workMinSpinner.getValue() == 0
				&& (int) workSecSpinner.getValue() == 0 && (int) restHourSpinner.getValue() == 0
				&& (int) restMinSpinner.getValue() == 0 && (int) restSecSpinner.getValue() == 0) {
			return;
		}
		if (workTimer.isRunning()) {
			workTimer.stop();
			pauseButton.setText("❙❙▶");
		} else if (restTimer.isRunning()) {
			restTimer.stop();
			pauseButton.setText("❙❙▶");
		} else {
			resumeTimers();
		}
	}

	void resumeTimers() {
		if (!workTimer.isRunning()) {
			workTimer.start();
			pauseButton.setText("❙❙");
		} else if (!restTimer.isRunning()) {
			restTimer.start();
			pauseButton.setText("❙❙");
		} else {
			pauseTimers();
		}
	}

	void resetTimers() {
		if (pauseButton.getText().equals("❙❙▶")) {
			pauseButton.setText("❙❙");
		}
		startButton.setEnabled(true);
		pauseButton.setEnabled(true);
		workTimer.stop();
		restTimer.stop();

		workHourSpinner.setValue(0);
		workMinSpinner.setValue(0);
		workSecSpinner.setValue(0);
		restHourSpinner.setValue(0);
		restMinSpinner.setValue(0);
		restSecSpinner.setValue(0);

		workRemainingSeconds = 0;
		restRemainingSeconds = 0;

		workTimerLabel.setForeground(Color.BLACK);
		restTimerLabel.setForeground(Color.BLACK);
		workTimerLabel.setText("00:00:00");
		restTimerLabel.setText("00:00:00");
	}

	void updateWorkTimer() {
		if (workRemainingSeconds > 0) {
			workRemainingSeconds--;
			workTimerLabel.setText(formatTime(workRemainingSeconds));
		} else {
			workTimer.stop();
			if ((int) restHourSpinner.getValue() == 0 && (int) restMinSpinner.getValue() == 0
					&& (int) restSecSpinner.getValue() == 0) {
				workTimerLabel.setText("Time's up!");
				workTimerLabel.setForeground(Color.RED);
			} else {
				workTimerLabel.setText("Rest starts now!");
				restRemainingSeconds = (int) restHourSpinner.getValue() * 3600 + (int) restMinSpinner.getValue() * 60
						+ (int) restSecSpinner.getValue();
				restTimer.start();
			}
		}
		if (workRemainingSeconds == 0) {
			pauseButton.setEnabled(false);
		}
	}

	void updateRestTimer() {
		if (restRemainingSeconds > 0) {
			restRemainingSeconds--;
			restTimerLabel.setText(formatTime(restRemainingSeconds));
		} else {
			restTimer.stop();
			restTimerLabel.setText("Time's up!");
			restTimerLabel.setForeground(Color.RED);
		}
	}

	String formatTime(int seconds) {
		int hours = seconds / 3600;
		int minutes = (seconds % 3600) / 60;
		int secs = seconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, secs);
	}
	
	static int getWorkCount() {
		return workCount;
	}
	
	static int getRestCount() {
		return restCount;
	}
}
