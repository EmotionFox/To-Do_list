package emotion.fr.utils;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import emotion.fr.component.TPanel;
import emotion.fr.init.BaseFrame;

public class Data
{
	private List<TPanel> list = new ArrayList<TPanel>();
	private Path source;
	
	private BaseFrame baseFrame;

	public Data(BaseFrame baseFrame, String str)
	{
		this.baseFrame = baseFrame;
		source = Paths.get(str);
		if (source.toFile().exists())
			reload();
	}

	public int getSize()
	{
		return list.size();
	}

	public void add(TPanel panel)
	{
		list.add(panel);
		save();
	}

	public void remove(int index)
	{
		list.remove(index);
		save();
	}

	public TPanel get(int index)
	{
		return list.get(index);
	}

	public List<TPanel> getList()
	{
		return list;
	}

	private void reload()
	{
		try (InputStream is = Files.newInputStream(source); BufferedInputStream bis = new BufferedInputStream(is); DataInputStream dis = new DataInputStream(bis))
		{
			int size = dis.readInt();

			if (size > 0)
			{
				for (int i = 0; i < size; i++)
				{
					TPanel panel = new TPanel(baseFrame, i);
					panel.setText(dis.readUTF());
					panel.setToggle(dis.readBoolean());
					list.add(panel);
				}
			}

			int primary = dis.readInt();
			int secondary = dis.readInt();

			baseFrame.setColors(new Color(primary), new Color(secondary));

			TextManager.setLanguage(dis.readUTF());

			baseFrame.posX = dis.readInt();
			baseFrame.posY = dis.readInt();

			dis.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void clear()
	{
		list.clear();
		save();
	}

	public void save()
	{
		System.out.println("SAVING");
		
		try (OutputStream os = Files.newOutputStream(source); BufferedOutputStream bos = new BufferedOutputStream(os); DataOutputStream dos = new DataOutputStream(bos))
		{
			dos.writeInt(list.size());

			if (list.size() > 0)
			{
				for (int i = 0; i < list.size(); i++)
				{
					dos.writeUTF(list.get(i).getText());
					dos.writeBoolean(list.get(i).getToggle());
				}
			}

			dos.writeInt(baseFrame.getPrimaryColor().getRGB());
			dos.writeInt(baseFrame.getSecondaryColor().getRGB());

			dos.writeUTF(TextManager.getLanguage().getName());

			dos.writeInt(baseFrame.getX());
			dos.writeInt(baseFrame.getY());

			dos.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
