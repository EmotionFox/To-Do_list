package emotion.fr.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

import emotion.fr.init.BaseFrame;
import emotion.fr.init.MainPanel;

//Task Panel
public class TPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -9171079668376633627L;

	private JLabel label = new JLabel();
	private String text;
	private int index;

	private RButton removeButton = new RButton();
	private EToggleButton toggleButton = new EToggleButton();

	private BaseFrame baseFrame;

	public TPanel(BaseFrame baseFrame, int index)
	{
		this.baseFrame = baseFrame;
		this.index = index;

		this.setLayout(null);

		UIManager.put("ToolTip.background", new ColorUIResource(255, 255, 255));
		Border border = BorderFactory.createLineBorder(new Color(76, 79, 83));
		UIManager.put("ToolTip.border", border);
		
		label.setFont(new Font("Open Sans", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.white);
		label.setBounds(30, 0, 240, 30);

		removeButton.setBorderPainted(false);
		removeButton.setBackground(Color.white);
		removeButton.addActionListener(this);
		removeButton.setBounds(5, 5, 20, 20);

		toggleButton.setBorderPainted(false);
		toggleButton.setBackground(Color.white);
		toggleButton.addActionListener(this);
		toggleButton.setBounds(275, 5, 20, 20);

		this.add(label);
	}

	public void setText(String str)
	{
		this.text = str;

		label.setText(str);
		label.setToolTipText(str);

		this.add(removeButton, 0);
		this.add(toggleButton, 1);
	}

	public void setToggle(boolean bool)
	{
		toggleButton.setSelected(bool);
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public String getText()
	{
		return this.text;
	}

	public boolean getToggle()
	{
		return toggleButton.isSelected();
	}

	public int getIndex()
	{
		return this.index;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		if (toggleButton.isSelected())
		{
			g.setColor(Color.white);
			g.drawLine(30, 15, 270, 15);
			g.setColor(new Color(255, 255, 255, 50));
		}
		else
		{
			g.setColor(new Color(0, 0, 0, 100));
		}

		g.fillRect(0, 0, 300, this.getHeight());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == removeButton)
		{
			baseFrame.data.remove(this.index);

			if (baseFrame.getPanel() instanceof MainPanel)
			{
				MainPanel panel = (MainPanel) baseFrame.getPanel();
				panel.refreshTask();
			}
		}
		if (e.getSource() == toggleButton)
		{
			baseFrame.data.save();
		}
	}

	@Override
	public String toString()
	{
		return "Task: " + index + "; Text: " + this.text;
	}
}
