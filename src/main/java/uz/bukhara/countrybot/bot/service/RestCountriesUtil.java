package uz.bukhara.countrybot.bot.service;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import uz.bukhara.countrybot.bot.CountryBot;
import uz.bukhara.countrybot.bot.model.countryModel.Response;
import uz.bukhara.countrybot.bot.util.Constant;

import java.io.*;

public class RestCountriesUtil {

    public static Response[] getCountries(String search) throws IOException {
        String url="";
        switch (CountryBot.searchBy){
            case Constant.SEARCH_BY_NAME:url="name/";break;
            case Constant.SEARCH_BY_CAPITAL:url="capital/";break;
            case Constant.SEARCH_BY_LANGUAGE:url="lang/";break;
            case Constant.SEARCH_BY_CURRENCY:url="currency/";break;
            case Constant.SEARCH_BY_REGION:url="region/";break;
            case Constant.SEARCH_BY_CALLING_CODE:url="callingcode/";break;
        }

        HttpGet httpGet=new HttpGet("https://restcountries.eu/rest/v2/"+url+search);
        HttpClient client= HttpClients.createDefault();
        HttpResponse response=client.execute(httpGet);

        BufferedReader reader=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        return new Gson().fromJson(reader,Response[].class);
    }

    public static Response getCountryByAlpha2Code(String search) throws IOException {

        HttpGet httpGet=new HttpGet("https://restcountries.eu/rest/v2/alpha/"+search);
        HttpClient client= HttpClients.createDefault();
        HttpResponse response=client.execute(httpGet);

        BufferedReader reader=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        return new Gson().fromJson(reader,Response.class);
    }

    public static InputStream getFlagOfCountry() throws IOException {
        String baseBath="https://restcountries.eu/data/";
        Response country = getCountryByAlpha2Code(CountryBot.currentCountry);

        HttpGet httpGet=new HttpGet(baseBath+country.getAlpha3Code().toLowerCase()+".svg");
        HttpClient client=HttpClients.createDefault();
        HttpResponse response=client.execute(httpGet);

            return response.getEntity().getContent();
    }



}
