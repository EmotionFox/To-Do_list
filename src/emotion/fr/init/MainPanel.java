package emotion.fr.init;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JViewport;

import emotion.fr.component.MButton;
import emotion.fr.component.TPanel;
import emotion.fr.utils.FrameManager;
import emotion.fr.utils.TextManager;

public class MainPanel extends JPanel implements Update
{
	private static final long serialVersionUID = 4876062610504962855L;

	private JPanel taskPanel = new JPanel();
	private JViewport viewport = new JViewport();
	private int maxTask = 25;

	public MButton addButton;
	public MButton settingButton;
	public JTextField field = new JTextField();

	private ListenerAction buttonListener = new ListenerAction();
	private BaseFrame baseFrame;

	private boolean leftMouse = false;
	private boolean init = true;
	private int sourceX = 0;
	private int sourceY = 0;

	public MainPanel(BaseFrame baseFrame)
	{
		this.baseFrame = baseFrame;

		this.setLayout(null);
		this.setPreferredSize(new Dimension(300, 500));
		this.setSize(300, 500);

		addButton = new MButton(baseFrame, new Dimension(280, 30));
		addButton.addActionListener(buttonListener);
		addButton.setBounds(10, 10, 280, 30);

		settingButton = new MButton(baseFrame, new Dimension(280, 30));
		settingButton.addActionListener(buttonListener);
		settingButton.setBounds(10, 10, 280, 30);

		field.setBounds(0, 0, this.getWidth(), 30);
		field.setFont(new Font("Open Sans", Font.PLAIN, 18));
		field.setHorizontalAlignment(JLabel.CENTER);
		field.addKeyListener(new ListenerKey());
		field.setBorder(null);
		field.setVisible(false);
		field.setFocusable(true);

		taskPanel.setPreferredSize(new Dimension(this.getWidth(), 1030));
		taskPanel.setBounds(0, 0, this.getWidth(), 1030);
		taskPanel.setOpaque(false);
		taskPanel.setLayout(null);
		taskPanel.addMouseListener(new ListenerMouse());

		viewport.setBounds(0, 50, this.getWidth(), 400);
		viewport.setFocusable(true);
		viewport.setOpaque(false);
		viewport.addMouseWheelListener(new ListenerMouseWheel());
		viewport.setView(taskPanel);

		if (baseFrame.data.getSize() > 0)
			refreshTask();

		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, this.getWidth(), 50);
		topPanel.setBackground(Color.white);
		topPanel.setLayout(null);
		topPanel.add(addButton);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 450, this.getWidth(), 50);
		bottomPanel.setBackground(Color.white);
		bottomPanel.setLayout(null);
		bottomPanel.add(settingButton);

		this.add(topPanel);
		this.add(viewport);
		this.add(bottomPanel);
	}

	public void update()
	{
		addButton.setText(TextManager.getText(0));
		settingButton.setText(TextManager.getText(1));
		
		if (field.isVisible() || baseFrame.data.getSize() >= maxTask)
		{
			field.grabFocus();
			addButton.setEnabled(false);
		}
		else
		{
			addButton.setEnabled(true);
		}

		if (this.leftMouse)
		{
			int posX = baseFrame.getX();
			int posY = baseFrame.getY();

			if (init)
			{
				sourceX = (int) this.getMousePosition().getX();
				sourceY = (int) this.getMousePosition().getY();
				init = false;
			}

			try
			{
				int mouseX = (int) this.getMousePosition().getX();
				int mouseY = (int) this.getMousePosition().getY();

				baseFrame.setLocation((posX - sourceX) + mouseX, (posY - sourceY) + mouseY);
			} catch (NullPointerException npe)
			{
				leftMouse = false;
			}
		}
		else
			init = true;
	}

	public void addTask()
	{
		int pos = 10 + (40 * baseFrame.data.getSize());

		field.setText("");
		field.setEditable(true);
		field.setVisible(true);

		TPanel panel = new TPanel(baseFrame, baseFrame.data.getSize());
		panel.setBounds(0, pos, this.getWidth(), 30);
		panel.add(field);

		if ((panel.getY() + panel.getHeight() + 20) > (viewport.getViewPosition().getY() + viewport.getHeight()))
			viewport.setViewPosition(new Point(0, ((panel.getY() + panel.getHeight()) + 20) - viewport.getHeight()));

		taskPanel.add(panel);
	}

	public void refreshTask()
	{
		taskPanel.removeAll();

		for (int i = 0; i < baseFrame.data.getSize(); i++)
		{
			int pos = 10 + (40 * i);

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

		g.setColor(Color.white);
		g.fillRect(10, 438, this.getWidth() - 20, 5);
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect((int) (viewport.getViewPosition().getY() / 630 * 280), 430, 20, 20);
	}

	private class ListenerAction implements ActionListener
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

	private class ListenerKey implements KeyListener
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

	public class ListenerMouse implements MouseListener
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
			if (e.getButton() == 3)
			{
				FrameManager.removeFrame(baseFrame);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			if (e.getButton() == 1)
				leftMouse = false;
		}
	}

	public class ListenerMouseWheel implements MouseWheelListener
	{
		private int yPos = 0;

		@Override
		public void mouseWheelMoved(MouseWheelEvent e)
		{
			yPos += e.getWheelRotation() * 15;

			if (yPos + viewport.getHeight() > 1030)
				yPos = (int) (viewport.getViewSize().getHeight() - viewport.getHeight());
			else if (yPos < 0)
				yPos = 0;

			viewport.setViewPosition(new Point(0, yPos));
		}
	}
}
