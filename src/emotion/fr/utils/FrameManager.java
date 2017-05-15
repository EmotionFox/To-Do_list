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
import java.util.List;

import emotion.fr.MainEmotion;
import emotion.fr.init.BaseFrame;

public abstract class FrameManager
{
	private static List<BaseFrame> frames = new ArrayList<BaseFrame>();
	private static Path source = Paths.get("./TaskSetting.bin");

	private static int extraList = 0;

	public static void init()
	{
		frames.add(new BaseFrame());

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
		for (int i = 0; i < extraList; i++)
		{
			frames.add(new BaseFrame());
		}
	}

	public static void removeFrame(BaseFrame frame)
	{
		frame.close();

		if (frames.isEmpty())
			MainEmotion.stop();
	}

	public static void removeList(BaseFrame frame)
	{
		Path source = Paths.get("./Task" + frame.getInstance() + ".bin");

		frame.close();

		if (source.toFile().exists())
			source.toFile().delete();

		extraList--;
		save();
	}

	public static void update()
	{
		for (BaseFrame frame : frames)
		{
			if (frame.isVisible())
				frame.update();
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
