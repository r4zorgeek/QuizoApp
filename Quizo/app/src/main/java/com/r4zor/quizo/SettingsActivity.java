package com.r4zor.quizo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    // strings
    String category;
    String difficulty;
    String qType;
    // ref for Spinners
    Spinner mCategorySpinner;
    Spinner mDifficultySpinner;
    Spinner mQTypeSpinner;
    // ref for Button
    Button mDoneButton;
    // Settings
    public static AppSettings mAppSettings;

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, SettingsActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAppSettings = new AppSettings();

        // get Category Spinner ref and set Array Adapter.
        mCategorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mAppSettings.getQuestionsCategoryArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategorySpinner.setAdapter(adapter);


        // get Difficulty Spinner ref and set Array Adapter.
        mDifficultySpinner = (Spinner) findViewById(R.id.difficultySpinner);
        ArrayAdapter<String> mDifficultyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mAppSettings.getQuestionsDifficultyArray());
        mDifficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDifficultySpinner.setAdapter(mDifficultyAdapter);

        // get Question Type Spinner ref and set Array Adapter.
        mQTypeSpinner = (Spinner) findViewById(R.id.qTypeSpinner);
        ArrayAdapter<String> mQTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mAppSettings.getQuesitonsTypeArray());
        mQTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mQTypeSpinner.setAdapter(mQTypeAdapter);


        mDoneButton = findViewById(R.id.doneButton);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = (String) mCategorySpinner.getSelectedItem();
                difficulty = (String) mDifficultySpinner.getSelectedItem();
                qType = (String) mQTypeSpinner.getSelectedItem();
                mAppSettings.setQuestionsCategory(category);
                mAppSettings.setQuestionsDifficulty(difficulty);
                mAppSettings.setQuestionsType(qType);
                Log.d("Category", category);
                Log.d("Difficulty", difficulty);
                Log.d("Question Type", qType);
                setWasDataSaved();
                SettingsActivity.super.finish();
            }
        });


    }

    private void setWasDataSaved() {
        Intent data = new Intent();
        setResult(1, data);
    }

}
