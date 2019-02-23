package com.r4zor.quizo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mane on 01/11/18.
 */

public class MultipleChoiceQuestionsFragment extends Fragment implements View.OnClickListener{
    private QuestionsActivity mQuestionsActivity;
    private Button mButtonOp1;
    private Button mButtonOp2;
    private Button mButtonOp3;
    private Button mButtonOp4;
    private TextView mQuestionView;
    private Button mNext;
    String correctAnswer;
    ArrayList<String> mAnswers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.multiple_choice_fragment_layout, container, false);
        mAnswers = new ArrayList<>();
        mQuestionsActivity = (QuestionsActivity) getActivity();
        mQuestionView = (TextView) v.findViewById(R.id.mcQuestion);
        mButtonOp1 = (Button) v.findViewById(R.id.op1);
        mButtonOp2 = (Button) v.findViewById(R.id.op2);
        mButtonOp3 = (Button) v.findViewById(R.id.op3);
        mButtonOp4 = (Button) v.findViewById(R.id.op4);
        mNext = (Button) v.findViewById(R.id.mcNextButton);
        try {
            mQuestionView.setText(Uri.decode(Questions.questionArray.getJSONObject(mQuestionsActivity.mCurrentIndex).get("question").toString()));
            correctAnswer = Uri.decode(Questions.questionArray.getJSONObject(mQuestionsActivity.mCurrentIndex).get("correct_answer").toString());
            JSONArray j = Questions.questionArray.getJSONObject(mQuestionsActivity.mCurrentIndex).getJSONArray("incorrect_answers");
            //Log.d("Incorrect", Uri.decode(Questions.questionArray.getJSONObject(mQuestionsActivity.mCurrentIndex).get("incorrect_answers").toString()));
            for (int i = 0; i < j.length(); i++) {
                mAnswers.add(Uri.decode(j.get(i).toString()));
            }
            mAnswers.add(correctAnswer);
            Collections.shuffle(mAnswers);
            mButtonOp1.setText(mAnswers.get(0));
            mButtonOp2.setText(mAnswers.get(1));
            mButtonOp3.setText(mAnswers.get(2));
            mButtonOp4.setText(mAnswers.get(3));
        }
        catch (Exception e) {}

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionsActivity.updateQuestion();
            }
        });

        mButtonOp1.setOnClickListener(this);
        mButtonOp2.setOnClickListener(this);
        mButtonOp3.setOnClickListener(this);
        mButtonOp4.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        Button n = (Button) view;
        if (n.getText().equals(correctAnswer)) {
            Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
            mQuestionsActivity.totalScore += 1;
            mQuestionsActivity.updateQuestion();
        }
        else {
            Toast.makeText(getContext(), "Wrong", Toast.LENGTH_SHORT).show();
            mQuestionsActivity.updateQuestion();
        }
    }
}
