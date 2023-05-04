package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class DashboardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public DashboardPanel() {
		// Set background color
		Color backgroundColor = Color.decode("#50525e");
		Color panelColor = Color.decode("#858582");
		Color scrollColor = Color.decode("#808080");
		Dimension panelSize = new Dimension(400, 290);
		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
		Border loweredBevel = BorderFactory.createLoweredBevelBorder();
		Border border = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
		
		border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5);

		setBackground(backgroundColor);
		setBackground(backgroundColor);

		// Set layout
		setLayout(new GridBagLayout());

		// Create panels
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(panelSize);
		leftPanel.setBackground(panelColor);
		leftPanel.setBorder(border);
		leftPanel.setLayout(null);

		// Add welcome message to left panel
		JLabel welcomeLabel = new JLabel("Welcome back!");
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
		welcomeLabel.setForeground(Color.WHITE);

		// Get the size of the label
		Dimension welcomeLabelSize = welcomeLabel.getPreferredSize();

		// Calculate the position of the label at the center of the panel
		int welcomeLabelX = (panelSize.width - welcomeLabelSize.width) / 2;
		int welcomeLabelY = (panelSize.height - welcomeLabelSize.height) / 2 - 100;

		welcomeLabel.setBounds(welcomeLabelX, welcomeLabelY, welcomeLabelSize.width, welcomeLabelSize.height);
		leftPanel.add(welcomeLabel);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.setPreferredSize(panelSize);
		rightPanel.setBackground(panelColor);
		rightPanel.setBorder(border);
		
		// Create label for work timers completed
		JLabel workLabel = new JLabel("Work timers completed: " + TimerPanel.getWorkCount());
		workLabel.setFont(new Font("Arial", Font.BOLD, 18));
		workLabel.setForeground(Color.WHITE);
		rightPanel.add(workLabel);

		// Create label for rest timers completed
		JLabel restLabel = new JLabel("Rest timers completed: " + TimerPanel.getRestCount());
		restLabel.setFont(new Font("Arial", Font.BOLD, 18));
		restLabel.setForeground(Color.WHITE);
		rightPanel.add(restLabel);
		
		GridBagConstraints c = new GridBagConstraints();
		
		// Add work label
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 40, 0); 
		rightPanel.add(workLabel, c);

		// Add rest label
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		rightPanel.add(restLabel, c);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(panelColor);
		bottomPanel.setPreferredSize(new Dimension(1200, 300));
		bottomPanel.setBorder(border);
		
		JScrollPane scrollPane = new JScrollPane(bottomPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setBackground(Color.decode("#a3a59e"));
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = scrollColor;
		    }
		});
		
		// Add left panel
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		add(leftPanel, c);

		// Add right panel
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		add(rightPanel, c);

		// Add bottom panel
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		add(bottomPanel, c);
	}
}
