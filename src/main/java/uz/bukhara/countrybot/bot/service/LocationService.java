package uz.bukhara.countrybot.bot.service;

import com.google.gson.Gson;
import uz.bukhara.countrybot.bot.model.locationModels.LocationsItem;
import uz.bukhara.countrybot.bot.model.locationModels.MapQuest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LocationService {
    private static final String url = "https://open.mapquestapi.com/geocoding/v1/reverse?key=iFaKV4Rp3rWRtGeJoQQYBEUnqJcxF8Cl&location=";

    public static String getCountry(Float lat, Float lon) {
        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();
        String country = null;
        try {
            URL url1 = new URL(url + lat + "," + lon);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                stringBuilder.append(row);
            }
            MapQuest mapQuest = gson.fromJson(stringBuilder.toString(), MapQuest.class);
            LocationsItem locationsItem = mapQuest.getResults().get(0).getLocations().get(0);
            country = locationsItem.getAdminArea1();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return country;
    }
}
