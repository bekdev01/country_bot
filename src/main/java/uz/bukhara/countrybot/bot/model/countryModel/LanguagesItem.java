package uz.bukhara.countrybot.bot.model.countryModel;

import com.google.gson.annotations.SerializedName;

public class LanguagesItem{

	@SerializedName("nativeName")
	private String nativeName;

	@SerializedName("iso639_2")
	private String iso6392;

	@SerializedName("name")
	private String name;

	@SerializedName("iso639_1")
	private String iso6391;

	public String getNativeName(){
		return nativeName;
	}

	public String getIso6392(){
		return iso6392;
	}

	public String getName(){
		return name;
	}

	public String getIso6391(){
		return iso6391;
	}
}