package emotion.fr.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import emotion.fr.init.BaseFrame;

//Color Button
public class CButton extends JButton implements ActionListener, MouseListener
{
	private static final long serialVersionUID = 8619288551690385562L;

	private Color fontColor = new Color(0, 0, 0, 100);
	private Color[] colors = new Color[2];

	private BaseFrame baseFrame;

	public CButton(BaseFrame baseFrame, Color primary, Color secondary)
	{
		this.baseFrame = baseFrame;
		this.colors[0] = primary;
		this.colors[1] = secondary;
		this.setOpaque(false);
		this.setBorder(null);
		this.addActionListener(this);
		this.addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0, 0, colors[0], this.getWidth(), this.getHeight(), colors[1]);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(fontColor);
		g2d.fillOval(1, 1, this.getWidth() - 2, this.getHeight() - 2);
		g2d.setPaint(gp);
		g2d.fillOval(5, 5, this.getWidth() - 10, this.getHeight() - 10);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		baseFrame.setColors(colors[0], colors[1]);
		baseFrame.data.save();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		this.fontColor = new Color(255, 255, 255, 100);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		this.fontColor = new Color(0, 0, 0, 100);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		this.fontColor = new Color(255, 255, 255);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.fontColor = new Color(255, 255, 255, 100);
	}
}
