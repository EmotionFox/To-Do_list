package emotion.fr.init;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import emotion.fr.component.CButton;
import emotion.fr.component.MButton;
import emotion.fr.utils.FrameManager;
import emotion.fr.utils.TextManager;

public class SettingPanel extends JPanel implements Update
{
	private static final long serialVersionUID = -6523309950648625927L;

	public MButton clearButton;
	public MButton newButton;
	public MButton deleteButton;
	public MButton languageButton;
	public MButton defaultButton;
	public MButton returnButton;

	private BaseFrame baseFrame;
	private BaseListener listener = new BaseListener();

	public SettingPanel(BaseFrame baseFrame)
	{
		this.baseFrame = baseFrame;
		this.setLayout(null);

		clearButton = new MButton(baseFrame, new Dimension(280, 30));
		clearButton.addActionListener(listener);
		clearButton.setBounds(10, 10, 280, 30);
		newButton = new MButton(baseFrame, new Dimension(280, 30));
		newButton.addActionListener(listener);
		newButton.setBounds(10, 50, 280, 30);
		deleteButton = new MButton(baseFrame, new Dimension(280, 30));
		deleteButton.addActionListener(listener);
		deleteButton.setBounds(10, 90, 280, 30);
		deleteButton.setEnabled(false);
		languageButton = new MButton(baseFrame, new Dimension(280, 30));
		languageButton.addActionListener(listener);
		languageButton.setBounds(10, 130, 280, 30);
		defaultButton = new MButton(baseFrame, new Dimension(280, 30));
		defaultButton.addActionListener(listener);
		defaultButton.setBounds(10, 170, 280, 30);
		returnButton = new MButton(baseFrame, new Dimension(280, 30));
		returnButton.addActionListener(listener);
		returnButton.setBounds(10, 10, 280, 30);

		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 300, 210);
		topPanel.setBackground(Color.white);
		topPanel.setLayout(null);
		topPanel.add(clearButton);
		topPanel.add(newButton);
		topPanel.add(deleteButton);
		topPanel.add(languageButton);
		topPanel.add(defaultButton);

		CButton orangeButton = new CButton(baseFrame, new Color(0xd67c56), new Color(0x94563b));
		orangeButton.setBounds(5, 0, 50, 50);

		CButton yellowButton = new CButton(baseFrame, new Color(0x94883b), new Color(0x696029));
		yellowButton.setBounds(65, 0, 50, 50);

		CButton greenButton = new CButton(baseFrame, new Color(0x3b954e), new Color(0x296836));
		greenButton.setBounds(125, 0, 50, 50);

		CButton blueButton = new CButton(baseFrame, new Color(0x5583d6), new Color(0x3b5b95));
		blueButton.setBounds(185, 0, 50, 50);

		CButton purpleButton = new CButton(baseFrame, new Color(0xd655d1), new Color(0x953b93));
		purpleButton.setBounds(245, 0, 50, 50);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 215, 300, 50);
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(null);
		buttonPanel.add(orangeButton);
		buttonPanel.add(yellowButton);
		buttonPanel.add(greenButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(purpleButton);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 450, 300, 50);
		bottomPanel.setBackground(Color.white);
		bottomPanel.setLayout(null);
		bottomPanel.add(returnButton);

		this.add(topPanel);
		this.add(buttonPanel);
		this.add(bottomPanel);
	}

	public void update()
	{
		if (baseFrame.getCurrentInstance() > 1 && baseFrame.getCurrentInstance() == baseFrame.getInstance())
			deleteButton.setEnabled(true);
		else
			deleteButton.setEnabled(false);

		clearButton.setText(TextManager.getText(2));
		newButton.setText(TextManager.getText(3));
		deleteButton.setText(TextManager.getText(4));
		languageButton.setText(TextManager.getText(5));
		defaultButton.setText(TextManager.getText(6));
		returnButton.setText(TextManager.getText(7));
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint paint = new GradientPaint(0, 70, baseFrame.getPrimaryColor(), 0, this.getHeight(), baseFrame.getSecondaryColor());
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	private class BaseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == clearButton)
				baseFrame.data.clear();
			else if (e.getSource() == newButton)
			{
				FrameManager.addFrame();

				if (baseFrame.getInstance() == 0)
					baseFrame.data.save();
			}
			else if (e.getSource() == deleteButton)
			{
				FrameManager.removeList(baseFrame);
			}
			else if (e.getSource() == languageButton)
			{
				TextManager.switchLanguage();
			}
			else if (e.getSource() == defaultButton)
			{
				baseFrame.setColors(new Color(0xd65555), new Color(0x953b3b));
				baseFrame.data.save();
			}
			else if (e.getSource() == returnButton)
				baseFrame.switchPanel(new MainPanel(baseFrame));
		}
	}
}
