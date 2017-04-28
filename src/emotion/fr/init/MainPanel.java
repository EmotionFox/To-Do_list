package emotion.fr.init;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import emotion.fr.component.MButton;
import emotion.fr.component.TPanel;

public class MainPanel extends JPanel
{
	private static final long serialVersionUID = 4876062610504962855L;

	private JPanel taskPanel = new JPanel();

	public MButton addButton;
	public MButton settingButton;
	public JTextField field = new JTextField();

	private ButtonListener buttonListener = new ButtonListener();
	private BaseFrame baseFrame;

	public MainPanel(BaseFrame baseFrame)
	{
		this.baseFrame = baseFrame;

		this.setLayout(null);
		this.setPreferredSize(new Dimension(300, 500));
		this.setSize(300, 500);

		addButton = new MButton(baseFrame, baseFrame.localization[0]);
		addButton.addActionListener(buttonListener);
		addButton.setBounds(10, 10, 280, 30);

		settingButton = new MButton(baseFrame, baseFrame.localization[1]);
		settingButton.addActionListener(buttonListener);
		settingButton.setBounds(10, 10, 280, 30);

		field.setBounds(0, 0, this.getWidth(), 30);
		field.setFont(new Font("Open Sans", Font.PLAIN, 18));
		field.setHorizontalAlignment(JLabel.CENTER);
		field.addKeyListener(new keyboardListener());
		field.setBorder(null);
		field.setVisible(false);
		field.setFocusable(true);

		taskPanel.setBounds(0, 50, this.getWidth(), 400);
		taskPanel.setOpaque(false);
		taskPanel.setLayout(null);

		if (baseFrame.data.getSize() > 0)
			refreshTask();

		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, this.getWidth(), 50);
		topPanel.setLayout(null);
		topPanel.add(addButton);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 450, this.getWidth(), 50);
		bottomPanel.setLayout(null);
		bottomPanel.add(settingButton);

		this.add(topPanel);
		this.add(taskPanel);
		this.add(bottomPanel);
	}

	public void update()
	{
		if (field.isVisible() || baseFrame.data.getSize() >= 9)
		{
			field.grabFocus();
			addButton.setEnabled(false);
		}
		else
		{
			addButton.setEnabled(true);
		}
	}

	public void addTask()
	{
		int pos = 25 + (40 * baseFrame.data.getSize());

		field.setText("");
		field.setEditable(true);
		field.setVisible(true);

		TPanel panel = new TPanel(baseFrame, baseFrame.data.getSize());
		panel.setBounds(0, pos, this.getWidth(), 30);
		panel.add(field);

		taskPanel.add(panel);
	}

	public void refreshTask()
	{
		taskPanel.removeAll();

		for (int i = 0; i < baseFrame.data.getSize(); i++)
		{
			int pos = 25 + (40 * i);

			TPanel panel = baseFrame.data.get(i);
			panel.setIndex(i);
			panel.setBounds(0, pos, this.getWidth(), 30);

			taskPanel.add(panel);
		}
	}

	public void addLabel(String str)
	{
		TPanel panel = (TPanel) taskPanel.getComponent(taskPanel.getComponentCount() - 1);
		panel.setText(str);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint paint = new GradientPaint(0, 70, baseFrame.getPrimaryColor(), 0, this.getHeight(), baseFrame.getSecondaryColor());

		g2d.setPaint(paint);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == addButton)
				addTask();
			else if (e.getSource() == settingButton)
				baseFrame.switchPanel(new SettingPanel(baseFrame));
		}
	}

	private class keyboardListener implements KeyListener
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			// Enter
			if (e.getKeyCode() == 10)
			{
				if (field.getText().length() > 0)
				{
					addLabel(field.getText());

					// Get the last component
					TPanel panel = (TPanel) taskPanel.getComponent(taskPanel.getComponentCount() - 1);
					panel.setText(field.getText());
					panel.setToggle(false);
					baseFrame.data.add(panel);

					field.setVisible(false);
				}
			}

			// Escape
			if (e.getKeyCode() == 27)
			{
				taskPanel.remove(taskPanel.getComponentCount() - 1);
				field.setVisible(false);
			}
		}

		@Override
		public void keyReleased(KeyEvent e)
		{}

		@Override
		public void keyTyped(KeyEvent e)
		{}
	}
}