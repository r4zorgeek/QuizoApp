package com.r4zor.quizo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity {
    private TextView mTextView;

    public static Intent newIntent(Context context, int score) {
        Intent i = new Intent(context, EndGameActivity.class);
        i.putExtra("Total Score", score);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        mTextView = (TextView) findViewById(R.id.playerScore);
        Intent d = getIntent();
        int i = d.getIntExtra("Total Score", 0);
        mTextView.setText("Total Score: " + Integer.toString(i));
    }
}
