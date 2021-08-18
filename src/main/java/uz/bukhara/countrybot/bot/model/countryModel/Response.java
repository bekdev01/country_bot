package uz.bukhara.countrybot.bot.model.countryModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("area")
	private double area;

	@SerializedName("nativeName")
	private String nativeName;

	@SerializedName("capital")
	private String capital;

	@SerializedName("demonym")
	private String demonym;

	@SerializedName("flag")
	private String flag;

	@SerializedName("alpha2Code")
	private String alpha2Code;

	@SerializedName("languages")
	private List<LanguagesItem> languages;

	@SerializedName("borders")
	private List<String> borders;

	@SerializedName("subregion")
	private String subregion;

	@SerializedName("callingCodes")
	private List<String> callingCodes;

	@SerializedName("regionalBlocs")
	private List<Object> regionalBlocs;

	@SerializedName("gini")
	private double gini;

	@SerializedName("population")
	private int population;

	@SerializedName("numericCode")
	private String numericCode;

	@SerializedName("alpha3Code")
	private String alpha3Code;

	@SerializedName("topLevelDomain")
	private List<String> topLevelDomain;

	@SerializedName("timezones")
	private List<String> timezones;

	@SerializedName("cioc")
	private String cioc;

	@SerializedName("translations")
	private Translations translations;

	@SerializedName("name")
	private String name;

	@SerializedName("altSpellings")
	private List<String> altSpellings;

	@SerializedName("region")
	private String region;

	@SerializedName("latlng")
	private List<Double> latlng;

	@SerializedName("currencies")
	private List<CurrenciesItem> currencies;

	public double getArea(){
		return area;
	}

	public String getNativeName(){
		return nativeName;
	}

	public String getCapital(){
		return capital;
	}

	public String getDemonym(){
		return demonym;
	}

	public String getFlag(){
		return flag;
	}

	public String getAlpha2Code(){
		return alpha2Code;
	}

	public List<LanguagesItem> getLanguages(){
		return languages;
	}

	public List<String> getBorders(){
		return borders;
	}

	public String getSubregion(){
		return subregion;
	}

	public List<String> getCallingCodes(){
		return callingCodes;
	}

	public List<Object> getRegionalBlocs(){
		return regionalBlocs;
	}

	public double getGini(){
		return gini;
	}

	public int getPopulation(){
		return population;
	}

	public String getNumericCode(){
		return numericCode;
	}

	public String getAlpha3Code(){
		return alpha3Code;
	}

	public List<String> getTopLevelDomain(){
		return topLevelDomain;
	}

	public List<String> getTimezones(){
		return timezones;
	}

	public String getCioc(){
		return cioc;
	}

	public Translations getTranslations(){
		return translations;
	}

	public String getName(){
		return name;
	}

	public List<String> getAltSpellings(){
		return altSpellings;
	}

	public String getRegion(){
		return region;
	}

	public List<Double> getLatlng(){
		return latlng;
	}

	public List<CurrenciesItem> getCurrencies(){
		return currencies;
	}
}