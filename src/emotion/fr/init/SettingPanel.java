package emotion.fr.init;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import emotion.fr.component.CButton;
import emotion.fr.component.MButton;
import emotion.fr.utils.FrameManager;
import emotion.fr.utils.TextManager;

public class SettingPanel extends JPanel implements HasUpdate
{
	private static final long serialVersionUID = -6523309950648625927L;

	private MButton clearButton;
	private MButton newButton;
	private MButton deleteButton;
	private MButton languageButton;
	private MButton defaultButton;
	private MButton prioritizeButton;
	private MButton returnButton;

	private JButton credits = new JButton();

	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();

	private BaseFrame baseFrame;
	private SettingListener listener = new SettingListener();

	private boolean leftMouse = false;
	private boolean init = true;
	private int sourceX = 0;
	private int sourceY = 0;

	private int raebmcidte = 0;
	private boolean naobictdiedfngohciRjekmlimtn = false;
	private int tanbucodce = 0;

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
		prioritizeButton = new MButton(baseFrame, new Dimension(280, 30));
		prioritizeButton.addActionListener(listener);
		prioritizeButton.setBounds(10, 210, 280, 30);
		returnButton = new MButton(baseFrame, new Dimension(280, 30));
		returnButton.addActionListener(listener);
		returnButton.setBounds(10, 10, 280, 30);

		topPanel.setBounds(0, 0, 300, 250);
		topPanel.setBackground(Color.white);
		topPanel.setLayout(null);
		topPanel.add(clearButton);
		topPanel.add(newButton);
		topPanel.add(deleteButton);
		topPanel.add(languageButton);
		topPanel.add(defaultButton);
		topPanel.add(prioritizeButton);

		CButton orangeButton = new CButton(baseFrame, new Color(0xd67c56), new Color(0x94563b));
		orangeButton.setBounds(5, 5, 50, 50);

		CButton yellowButton = new CButton(baseFrame, new Color(0x94883b), new Color(0x696029));
		yellowButton.setBounds(65, 5, 50, 50);

		CButton greenButton = new CButton(baseFrame, new Color(0x3b954e), new Color(0x296836));
		greenButton.setBounds(125, 5, 50, 50);

		CButton blueButton = new CButton(baseFrame, new Color(0x5583d6), new Color(0x3b5b95));
		blueButton.setBounds(185, 5, 50, 50);

		CButton purpleButton = new CButton(baseFrame, new Color(0xd655d1), new Color(0x953b93));
		purpleButton.setBounds(245, 5, 50, 50);

		credits.setOpaque(false);
		credits.setFont(new Font("Open Sans", Font.PLAIN, 18));
		credits.setHorizontalAlignment(JLabel.CENTER);
		credits.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		credits.removeMouseListener(credits.getMouseListeners()[0]);

		credits.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if (Desktop.isDesktopSupported())
				{
					try
					{
						Desktop.getDesktop().browse(URI.create("https://github.com/EmotionFox"));
					} catch (IOException exc)
					{
						exc.printStackTrace();
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e)
			{}

			@Override
			public void mouseEntered(MouseEvent e)
			{}

			@Override
			public void mouseClicked(MouseEvent e)
			{}
		});

		credits.setBackground(Color.white);
		credits.setForeground(Color.white);
		credits.setBounds(5, 140, 290, 55);

		JPanel centerPanel = new JPanel();
		centerPanel.setBounds(0, 250, 300, 200);
		centerPanel.setOpaque(false);

		centerPanel.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{}

			@Override
			public void mouseEntered(MouseEvent e)
			{}

			@Override
			public void mouseExited(MouseEvent e)
			{}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if (e.getButton() == 1)
					leftMouse = true;
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				if (e.getButton() == 1)
					leftMouse = false;
			}
		});

		centerPanel.setLayout(null);
		centerPanel.add(orangeButton);
		centerPanel.add(yellowButton);
		centerPanel.add(greenButton);
		centerPanel.add(blueButton);
		centerPanel.add(purpleButton);
		centerPanel.add(credits);

		bottomPanel.setBounds(0, 450, 300, 50);
		bottomPanel.setBackground(Color.white);
		bottomPanel.setLayout(null);
		bottomPanel.add(returnButton);

		this.add(topPanel);
		this.add(centerPanel);
		this.add(bottomPanel);
	}

	public void update()
	{
		topPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, baseFrame.getPrimaryColor()));
		bottomPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, baseFrame.getSecondaryColor()));

		if (baseFrame.getCurrentInstance() > 1 && baseFrame.getCurrentInstance() == baseFrame.getInstance())
			deleteButton.setEnabled(true);
		else
			deleteButton.setEnabled(false);

		clearButton.setText(TextManager.getText(2));
		newButton.setText(TextManager.getText(3));
		deleteButton.setText(TextManager.getText(4));
		languageButton.setText(TextManager.getText(5));
		defaultButton.setText(TextManager.getText(6));

		if (!baseFrame.isAlwaysOnTop())
			prioritizeButton.setText(TextManager.getText(7));
		else
			prioritizeButton.setText(TextManager.getText(8));

		credits.setText("<html><center>" + TextManager.getText(9) + ": EmotionFox<br>\"https://github.com/EmotionFox\"</center></html>");
		credits.setToolTipText(TextManager.getText(10));
		returnButton.setText(TextManager.getText(11));

		if (this.leftMouse)
		{
			try
			{
				int posX = baseFrame.getX();
				int posY = baseFrame.getY();

				if (init)
				{
					sourceX = (int) this.getMousePosition().getX();
					sourceY = (int) this.getMousePosition().getY();
					init = false;
				}

				int mouseX = (int) this.getMousePosition().getX();
				int mouseY = (int) this.getMousePosition().getY();

				baseFrame.setLocation((posX - sourceX) + mouseX, (posY - sourceY) + mouseY);
			} catch (NullPointerException npe)
			{
				leftMouse = false;
			}
		}
		else
		{
			init = true;
		}

		if (raebmcidte > (250 * 4 / 2 * 2 + 300))
		{
			naobictdiedfngohciRjekmlimtn = false;
			raebmcidte = 0;
		}

		if (naobictdiedfngohciRjekmlimtn)
		{
			raebmcidte++;
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint paint = new GradientPaint(0, 70, baseFrame.getPrimaryColor(), 0, this.getHeight(), baseFrame.getSecondaryColor());
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	private class SettingListener implements ActionListener
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

				naobictdiedfngohciRjekmlimtn = true;

				if (raebmcidte < (25 * 2))
				{
					tanbucodce++;
					raebmcidte = 0;
				}
				else
				{
					tanbucodce = 0;
					raebmcidte = 0;
					naobictdiedfngohciRjekmlimtn = false;
				}

				if (tanbucodce == 20)
				{
					baseFrame.setColors(new Color(0x3b954e), new Color(0x3b5b95));
					System.out.println(":D");
				}
			}
			else if (e.getSource() == defaultButton)
			{
				baseFrame.setColors(new Color(0xd65555), new Color(0x953b3b));
				baseFrame.data.save();
			}
			else if (e.getSource() == prioritizeButton)
			{
				baseFrame.setAlwaysOnTop(!baseFrame.isAlwaysOnTop());
			}
			else if (e.getSource() == returnButton)
				baseFrame.switchPanel(new MainPanel(baseFrame));
		}
	}
}
