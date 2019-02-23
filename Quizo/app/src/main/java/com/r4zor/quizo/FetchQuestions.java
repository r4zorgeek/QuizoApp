package com.r4zor.quizo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by mane on 01/11/18.
 */

public class FetchQuestions extends AsyncTask<Context, Void, JSONArray> {
    Context mContext;
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
    JSONArray mJSONArray;

    @Override
    protected JSONArray doInBackground(Context... voids) {
        mContext = voids[0];
        Uri buildUri;
        URL requestUrl;
        JSONObject jB;
        JSONArray jA;
        try {
            buildUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(AMOUNT_QUESTIONS, "10")
                    .appendQueryParameter(CATEGORY, SettingsActivity.mAppSettings.getQuestionsCategoryId())
                    .appendQueryParameter(QUESTIONS_DIFFICULTY, SettingsActivity.mAppSettings.getQuestionsDifficulty())
                    .appendQueryParameter(QUESTIONS_TYPE, SettingsActivity.mAppSettings.getQuestionsType())
                    .appendQueryParameter("encode", "url3986")
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
                Log.d("exception occured Fetch", e.toString());
            }
        }
        catch (Exception e ) {}
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray j) {
        Questions.questionArray = j;
        Intent i = QuestionsActivity.newIntent(mContext);
        mContext.startActivity(i);
    }
}
