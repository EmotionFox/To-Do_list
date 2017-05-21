package emotion.fr.utils;

public abstract class TextManager
{
	private static final String[] lang_fr =
	{ "Ajouter une nouvelle tâche", "Paramètre", "Vider la liste", "Nouvelle liste", "Supprimer la liste", "Langue: Français", "Réinitialiser les couleurs", "Priorisation: Désactivé", "Priorisation: Activé", "Créé par", "Cliquer ici pour être redirigé vers le site", "Retour" };
	private static final String[] lang_en =
	{ "Add a new task", "Setting", "Clear the list", "New list", "Delete the list", "Language: English", "Reset the colors", "Prioritization: Disabled", "Prioritization: Activated", "Created by", "Click here to be redirect to the site", "Return" };

	private static Language lang = Language.EN;

	public static final void setLanguage(String newLang)
	{
		if (newLang.equals("ENGLISH"))
			lang = Language.EN;
		else
			lang = Language.FR;
	}

	public static final void switchLanguage()
	{
		if (lang == Language.EN)
			lang = Language.FR;
		else
			lang = Language.EN;
	}

	public static final String getText(int index)
	{
		return lang.getList()[index];
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

		public String getName()
		{
			return this.name;
		}

		public String[] getList()
		{
			return this == EN ? lang_en : lang_fr;
		}
	}
}
