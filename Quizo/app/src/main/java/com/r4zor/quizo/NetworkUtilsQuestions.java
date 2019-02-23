package com.r4zor.quizo;

import android.net.Uri;
import android.os.Parcel;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by mane on 01/11/18.
 */

public class NetworkUtilsQuestions {
    // BASE_URL
    private static String BASE_URL = "https://opentdb.com/api.php?";
    // Parameter for amount of questions
    private static String AMOUNT_QUESTIONS = "amount";
    // Parameter for Difficulty of Questions
    private static String QUESTIONS_DIFFICULTY = "difficulty";
    // Parameter for Category of Questions
    private static String CATEGORY = "category";
    // Parameter for Type of Questions
    private static String QUESTIONS_TYPE = "type";
    static Uri buildUri;
    static URL requestUrl;
    static JSONObject jB;
    static JSONArray jA;

    static JSONArray getQuestions() throws IOException{
        buildUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(AMOUNT_QUESTIONS, "10")
                .appendQueryParameter(CATEGORY, SettingsActivity.mAppSettings.getQuestionsCategoryId())
                .appendQueryParameter(QUESTIONS_DIFFICULTY, SettingsActivity.mAppSettings.getQuestionsDifficulty())
                .appendQueryParameter(QUESTIONS_DIFFICULTY, SettingsActivity.mAppSettings.getQuestionsType())
                .build();

        requestUrl = new URL(buildUri.toString());
        Log.d("URL", buildUri.toString());
        URLConnection urlConnection = requestUrl.openConnection();
        HttpsURLConnection connection = (HttpsURLConnection) urlConnection;
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String urlString = "";
        String current;

        while((current = in.readLine()) != null) {
            urlString += current;
        }
        try {
            jB = new JSONObject(urlString);
            jA = jB.getJSONArray("results");
            return jA;
        }
        catch (Exception e) {
            return getQuestions();
        }
    }

}
