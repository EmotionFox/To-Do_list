package emotion.fr.utils;

public abstract class Localization
{
	private static final String[] lang_fr =
	{ "Ajouter une nouvelle tâche", "Paramètre", "Vider la liste", "Langue: Français", "Réinitialiser les couleurs", "Retour" };
	private static final String[] lang_en =
	{ "Add a new task", "Setting", "Clear the list", "Language: English", "Reset the colors", "Return" };

	private static Language lang = Language.EN;

	public static final void setLanguage(Language newLang)
	{
		lang = newLang;
	}
	
	public static final void setLanguage(String newLang)
	{
		if(newLang.equals("FRENCH"))
			lang = Language.FR;
		else
			lang = Language.EN;
	}

	public static final String[] getLocalization()
	{
		if (lang == Language.FR)
			return lang_fr;
		else
			return lang_en;
	}

	public static final Language getLanguage()
	{
		return lang;
	}

	public enum Language
	{
		FR("FRENCH"), EN("ENGLISH");

		private String name;

		private Language(String desc)
		{
			this.name = desc;
		}

		public String getDescription()
		{
			return this.name;
		}
	}
}
