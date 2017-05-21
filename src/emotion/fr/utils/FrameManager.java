package emotion.fr.utils;

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
import java.util.ConcurrentModificationException;
import java.util.List;

import emotion.fr.EmotionMain;
import emotion.fr.init.BaseFrame;

public abstract class FrameManager
{
	private static List<BaseFrame> frames = new ArrayList<BaseFrame>();
	private static Path source = Paths.get("./ETDL/TaskSetting.bin");

	private static int extraList = 0;

	public static void init()
	{
		Path path = Paths.get("./ETDL");

		frames.add(new BaseFrame());

		if (!path.toFile().exists())
		{
			try
			{
				Files.createDirectories(path);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		if (source.toFile().exists())
			reload();
	}

	public static void addFrame()
	{
		extraList++;
		frames.add(new BaseFrame());
		save();
	}

	public static void addExtra()
	{
		Path path;

		for (int i = 0; i < extraList; i++)
		{
			path = Paths.get("./ETDL/Task_" + (2 + i) + ".bin");

			if (!path.toFile().exists())
			{
				System.out.println("The list n°: " + (2 + i) + "; doesn't exist.");
			}

			frames.add(new BaseFrame());
		}
	}

	public static void removeFrame(BaseFrame frame)
	{
		frame.close();

		if (frame.getInstance() == 0)
		{
			save();
			EmotionMain.stop();
		}
	}

	public static void removeList(BaseFrame frame)
	{
		Path source = Paths.get("./ETDL/Task_" + frame.getInstance() + ".bin");

		frame.close();

		if (source.toFile().exists())
			source.toFile().delete();

		extraList--;
		save();
	}

	public static void update()
	{
		List<BaseFrame> list = frames;

		try
		{
			for (BaseFrame frame : list)
			{
				if (frame.isVisible())
					frame.update();
			}
		} catch (ConcurrentModificationException e)
		{
			return;
		}
	}

	public static void reload()
	{
		try (InputStream is = Files.newInputStream(source); BufferedInputStream bis = new BufferedInputStream(is); DataInputStream dis = new DataInputStream(bis))
		{
			extraList = dis.readInt();
			TextManager.setLanguage(dis.readUTF());
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		if (extraList > 0)
			addExtra();
	}

	public static void save()
	{
		try (OutputStream os = Files.newOutputStream(source); BufferedOutputStream bos = new BufferedOutputStream(os); DataOutputStream dos = new DataOutputStream(bos))
		{
			dos.writeInt(extraList);
			dos.writeUTF(TextManager.getLanguage().getName());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
