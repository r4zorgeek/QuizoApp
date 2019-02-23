package com.r4zor.quizo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by mane on 30/10/18.
 */

public class Categories {
    public static String[] categories;
    public static HashMap<String, String> categoriesMapping;

    /*

    public String[] getCategories() throws IOException {

            URL url = new URL("https://opentdb.com/api_category.php");
            URLConnection urlConnection = url.openConnection();
            HttpsURLConnection connection = (HttpsURLConnection) urlConnection;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current;

            while((current = in.readLine()) != null) {
                urlString += current;
            }
            try {
                JSONObject jB = new JSONObject(urlString);
                JSONArray jA = jB.getJSONArray("trivia_categories");
                categories = new String[jA.length()];

                for (int i = 0; i < jA.length(); i++) {
                    categories[i] = new String(jA.getJSONObject(i).getString("name"));
                }
            }
            catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return categories;
    }

    */

}
