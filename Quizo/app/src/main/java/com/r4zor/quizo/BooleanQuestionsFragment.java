package com.r4zor.quizo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.Collections;

/**
 * Created by mane on 01/11/18.
 */

public class BooleanQuestionsFragment extends Fragment implements View.OnClickListener {
    private QuestionsActivity mQuestionsActivity;
    private TextView mTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private String correctAnswer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.boolean_questions_frament_layout, container, false);

        mQuestionsActivity = new QuestionsActivity();
        mTextView = (TextView) v.findViewById(R.id.blQuestion);
        mTrueButton = (Button) v.findViewById(R.id.trueAnswer);
        mFalseButton = (Button) v.findViewById(R.id.falseAnswer);
        mNextButton = (Button) v.findViewById(R.id.blnextButton);

        try {
            mTextView.setText(Uri.decode(Questions.questionArray.getJSONObject(mQuestionsActivity.mCurrentIndex).get("question").toString()));
            correctAnswer = Uri.decode(Questions.questionArray.getJSONObject(mQuestionsActivity.mCurrentIndex).get("correct_answer").toString());
        }
        catch (Exception e) {}

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        if (b.getText().equals(correctAnswer)) {
            Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
            mQuestionsActivity.updateQuestion();
        }
        else {
            Toast.makeText(getContext(), "Wrong", Toast.LENGTH_SHORT).show();
            mQuestionsActivity.updateQuestion();
        }

        if (((Button) view).getText().equals("Next")) {
            mQuestionsActivity.updateQuestion();
        }
    }
}
