package emotion.fr;

import emotion.fr.utils.FrameManager;

public class MainEmotion
{
	private static boolean stop = false;

	public static void main(String[] args)
	{
		FrameManager.init();
		update();
	}

	public static void stop()
	{
		stop = true;
	}

	public static void update()
	{
		while (true)
		{
			if (stop)
				break;

			FrameManager.update();

			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		System.exit(0);
	}
}
