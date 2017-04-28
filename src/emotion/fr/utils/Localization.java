package emotion.fr.utils;

public final class Localization
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
		if(newLang.equals("ENGLISH"))
			lang = Language.EN;
		else
			lang = Language.FR;
	}

	public static final String[] getLocalization()
	{
		if (lang == Language.EN)
			return lang_en;
		else
			return lang_fr;
	}

	public static final Language getLanguage()
	{
		return lang;
	}

	public enum Language
	{
		FR("FRENCH"), EN("ENGLISH");

		private String desc;

		private Language(String desc)
		{
			this.desc = desc;
		}

		public String getDescription()
		{
			return this.desc;
		}
	}
}
