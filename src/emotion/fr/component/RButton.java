package emotion.fr.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

//Remove Button
public class RButton extends JButton
{
	private static final long serialVersionUID = -6320397506434384340L;

	private Image image;

	public RButton()
	{
		try
		{
			image = ImageIO.read(getClass().getResource("/resources/images/remove_button.png"));
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
		g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
