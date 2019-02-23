package com.r4zor.quizo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class QuestionsActivity extends AppCompatActivity {
    //static Questions mQuestions;
    public static int mCurrentIndex = 0;
    public int totalScore = 0;
    android.support.v4.app.FragmentManager mFragmentManager;
    android.support.v4.app.FragmentManager fm;

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, QuestionsActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        //mQuestions = new Questions();


        try {
            //Log.d("Questions", Questions.questionArray.toString());
            if (Questions.questionArray.getJSONObject(mCurrentIndex).get("type").equals("multiple")) {
                mFragmentManager = getSupportFragmentManager();
                mFragmentManager.beginTransaction().add(R.id.fragment_container, new MultipleChoiceQuestionsFragment()).commit();
            }
            else if (Questions.questionArray.getJSONObject(mCurrentIndex).get("type").equals("boolean")) {
                fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .add(R.id.fragment_container, new BooleanQuestionsFragment())
                        .commit();
            }
        }
        catch (Exception e) {}

    }

    public void updateQuestion() {
        if (mCurrentIndex + 1 == 10) {
            Intent i = EndGameActivity.newIntent(QuestionsActivity.this, totalScore);
            startActivity(i);
            finish();
        }
        mCurrentIndex = (mCurrentIndex + 1) % Questions.questionArray.length();

        try {
            //Log.d("Questions", Questions.questionArray.toString());
            if (Questions.questionArray.getJSONObject(mCurrentIndex).get("type").equals("multiple")) {
                mFragmentManager.beginTransaction().replace(R.id.fragment_container, new MultipleChoiceQuestionsFragment()).commit();
            }
            else if (Questions.questionArray.getJSONObject(mCurrentIndex).get("type").equals("boolean")) {
                fm.beginTransaction()
                        .replace(R.id.fragment_container, new BooleanQuestionsFragment())
                        .commit();
            }
        }
        catch (Exception e) {}

    }
}
