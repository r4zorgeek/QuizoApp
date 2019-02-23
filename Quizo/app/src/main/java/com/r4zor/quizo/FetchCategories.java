package com.r4zor.quizo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by mane on 31/10/18.
 */

public class FetchCategories extends AsyncTask<Void, Void, String[]> {


    @Override
    protected String[] doInBackground(Void... voids) {
        try {
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
                Categories.categories = new String[jA.length()];
                Categories.categoriesMapping = new HashMap<>();

                for (int i = 0; i < jA.length(); i++) {
                    Categories.categories[i] = new String(jA.getJSONObject(i).getString("name"));
                    Categories.categoriesMapping.put(Categories.categories[i], jA.getJSONObject(i).getString("id"));
                }
            }
            catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return Categories.categories;
        }
        catch (Exception e) {}
        return null;
    }
}
