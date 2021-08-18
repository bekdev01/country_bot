package uz.bukhara.countrybot.bot.model.countryModel;

import com.google.gson.annotations.SerializedName;

public class Translations{

	@SerializedName("br")
	private String br;

	@SerializedName("de")
	private String de;

	@SerializedName("pt")
	private String pt;

	@SerializedName("ja")
	private String ja;

	@SerializedName("hr")
	private String hr;

	@SerializedName("it")
	private String it;

	@SerializedName("fa")
	private String fa;

	@SerializedName("fr")
	private String fr;

	@SerializedName("es")
	private String es;

	@SerializedName("nl")
	private String nl;

	public String getBr(){
		return br;
	}

	public String getDe(){
		return de;
	}

	public String getPt(){
		return pt;
	}

	public String getJa(){
		return ja;
	}

	public String getHr(){
		return hr;
	}

	public String getIt(){
		return it;
	}

	public String getFa(){
		return fa;
	}

	public String getFr(){
		return fr;
	}

	public String getEs(){
		return es;
	}

	public String getNl(){
		return nl;
	}
}