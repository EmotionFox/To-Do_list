package emotion.fr.init;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import emotion.fr.utils.Data;

public class BaseFrame extends JFrame
{
	private static final long serialVersionUID = 3815365356097167016L;

	private Image image;
	private Color[] fontColors =
	{ new Color(0xd65555), new Color(0x953b3b) };
	public Data data;
	public int posX;
	public int posY;

	private static int instance = 0;
	private int currentInstance = 0;

	public BaseFrame()
	{
		instance++;
		currentInstance = instance;
		data = new Data(this, "./Task" + instance + ".bin");

		try
		{
			image = ImageIO.read(getClass().getResource("/resources/images/icon.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		this.setIconImage(image);
		this.setTitle("To-Do List");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLocation(posX, posY);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setFocusable(true);
		this.setContentPane(new MainPanel(this));
		this.pack();
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

	public int getInstance()
	{
		return instance;
	}

	public int getCurrentInstance()
	{
		return currentInstance;
	}

	public void setColors(Color primary, Color secondary)
	{
		this.fontColors[0] = primary;
		this.fontColors[1] = secondary;
	}

	public void switchPanel(JPanel panel)
	{
		this.setContentPane(panel);
	}

	public void close()
	{
		instance--;
		this.data.save();
		this.setVisible(false);
	}

	public void update()
	{
		if (this.getContentPane() instanceof Update)
		{
			Update updatablePanel = (Update) this.getContentPane();
			updatablePanel.update();
		}

		this.getContentPane().revalidate();
		this.getContentPane().repaint();
	}
}
