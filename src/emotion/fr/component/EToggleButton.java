package emotion.fr.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JToggleButton;

public class EToggleButton extends JToggleButton
{
	private static final long serialVersionUID = 8638985103752063977L;

	private Image imageOff;
	private Image imageOn;

	public EToggleButton()
	{
		try
		{
			imageOff = ImageIO.read(getClass().getResource("/resources/images/toggle_button_off.png"));
			imageOn = ImageIO.read(getClass().getResource("/resources/images/toggle_button_on.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		if (this.isSelected())
			g2d.drawImage(imageOn, 0, 0, this.getWidth(), this.getHeight(), this);
		else
			g2d.drawImage(imageOff, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
