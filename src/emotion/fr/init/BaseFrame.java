package emotion.fr.init;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import emotion.fr.utils.Data;
import emotion.fr.utils.Localization;

public class BaseFrame extends JFrame
{
	private static final long serialVersionUID = 3815365356097167016L;

	private Image image;
	private Color[] fontColors =
	{ new Color(0xd65555), new Color(0x953b3b) };
	public Data data = new Data(this, "./task.bin");
	public String[] localization = Localization.getLocalization();
	public int posX;
	public int posY;

	public BaseFrame()
	{
		try
		{
			image = ImageIO.read(getClass().getResource("/resources/images/icon.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		this.setTitle("To-Do List");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLocation(posX, posY);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setContentPane(new MainPanel(this));
		this.pack();
		this.setIconImage(image);
		this.setVisible(true);
	}

	@Override
	protected void processWindowEvent(WindowEvent e)
	{
		if (e.getID() == 201) // When closing
			data.save();

		super.processWindowEvent(e);
	}

	public Color getPrimaryColor()
	{
		return fontColors[0];
	}

	public Color getSecondaryColor()
	{
		return fontColors[1];
	}

	public JPanel getPanel()
	{
		if (this.getContentPane() instanceof JPanel)
		{
			JPanel panel = (JPanel) this.getContentPane();
			return panel;
		}
		else
			return null;
	}

	public void setColors(Color primary, Color secondary)
	{
		this.fontColors[0] = primary;
		this.fontColors[1] = secondary;
	}

	public void refreshLocalization()
	{
		this.localization = Localization.getLocalization();
		this.data.save();
	}

	public void switchPanel(JPanel panel)
	{
		this.setContentPane(panel);
	}

	public void update()
	{
		while (true)
		{
			if (this.getContentPane() instanceof MainPanel)
			{
				MainPanel basePanel = (MainPanel) this.getContentPane();
				basePanel.update();
			}

			this.getContentPane().revalidate();
			this.getContentPane().repaint();

			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
