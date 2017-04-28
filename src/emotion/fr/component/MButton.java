package emotion.fr.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import emotion.fr.init.BaseFrame;

//Menu Button
public class MButton extends JButton implements MouseListener
{
	private static final long serialVersionUID = -1579343309712601032L;

	private Color color;
	
	private BaseFrame baseFrame;

	public MButton(BaseFrame baseFrame, String text)
	{
		this.baseFrame = baseFrame;
		this.color = baseFrame.getPrimaryColor();

		this.setLayout(null);
		this.setBorderPainted(false);
		this.setOpaque(false);
		this.addMouseListener(this);

		JLabel label = new JLabel(text);
		label.setFont(new Font("Open Sans", Font.PLAIN, 16));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.white);
		label.setBounds(0, 0, 280, 30);

		this.add(label);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		// Auto quick refresh
		if (color != baseFrame.getPrimaryColor() && color != baseFrame.getSecondaryColor() && color != Color.black)
			color = baseFrame.getPrimaryColor();

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(color);
		g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		this.color = baseFrame.getSecondaryColor();
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		this.color = baseFrame.getPrimaryColor();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		this.color = Color.black;
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.color = baseFrame.getSecondaryColor();
	}
}
