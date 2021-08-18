package uz.bukhara.countrybot.bot.model.countryModel;

import com.google.gson.annotations.SerializedName;

public class CurrenciesItem{

	@SerializedName("symbol")
	private Object symbol;

	@SerializedName("code")
	private String code;

	@SerializedName("name")
	private String name;

	public Object getSymbol(){
		return symbol;
	}

	public String getCode(){
		return code;
	}

	public String getName(){
		return name;
	}
}