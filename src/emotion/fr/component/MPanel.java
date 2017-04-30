package emotion.fr.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JViewport;

//Moving Panel
public class MPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JLabel firstLabel = new JLabel();
	private JLabel secondLabel = new JLabel();
	private JViewport firstView = new JViewport();
	private JViewport secondView = new JViewport();

	public MPanel()
	{
		this.setLayout(null);
		this.setOpaque(false);

		firstLabel.setHorizontalAlignment(JLabel.CENTER);
		firstLabel.setFont(new Font("Open Sans", Font.PLAIN, 18));
		firstLabel.setForeground(Color.white);

		secondLabel.setHorizontalAlignment(JLabel.CENTER);
		secondLabel.setFont(new Font("Open Sans", Font.PLAIN, 18));
		secondLabel.setForeground(Color.white);

		firstView.setOpaque(false);
		firstView.setBounds(0, 0, 240, 30);
		firstView.setView(firstLabel);

		secondView.setOpaque(false);
		secondView.setBounds(240, 0, 240, 30);
		secondView.setView(secondLabel);

		if (firstView.getViewSize().getWidth() < firstView.getWidth())
			firstView.setViewSize(new Dimension(firstView.getWidth(), firstView.getHeight()));
		if (secondView.getViewSize().getWidth() < secondView.getWidth())
			secondView.setViewSize(new Dimension(secondView.getWidth(), secondView.getHeight()));

		this.add(firstView);
		this.add(secondView);
	}

	public void setText(String str)
	{
		firstLabel.setText("  " + str + "  ");
		secondLabel.setText("  " + str + "  ");
	}

	private boolean first = true;
	private boolean second = false;
	private boolean grab = false;
	private int i = 0;
	private int x = 0;

	public void move()
	{
		double topRight1 = firstView.getViewPosition().getX() + firstView.getWidth();
		double topRight2 = secondView.getViewPosition().getX() + secondView.getWidth();
		double topRight3 = firstView.getX() + firstView.getWidth();
		double topRight4 = secondView.getX() + secondView.getWidth();

		i++;

		if (i == 10)
		{
			if (grab)
			{
				if (first && topRight3 <= 0)
				{
					firstView.setViewPosition(new Point(0, 0));
					first = false;
					second = true;
					grab = false;
					x = 0;
				}
				else if (second && topRight4 <= 0)
				{
					secondView.setViewPosition(new Point(0, 0));
					first = true;
					second = false;
					grab = false;
					x = 0;
				}
				else if (first)
				{
					firstView.setLocation(x, 0);
					secondView.setLocation(firstView.getWidth() + x, 0);
				}
				else if (second)
				{
					secondView.setLocation(x, 0);
					firstView.setLocation(secondView.getWidth() + x, 0);
				}
			}
			else
			{
				if (first && topRight1 < firstView.getViewSize().getWidth())
				{
					firstView.setViewPosition(new Point(x, 0));
				}
				else if (second && topRight2 < secondView.getViewSize().getWidth())
				{
					secondView.setViewPosition(new Point(x, 0));
				}
				else
				{
					grab = true;
					x = 0;
				}
			}

			if (!grab)
				x++;
			else
				x--;

			i = 0;
		}
	}
}