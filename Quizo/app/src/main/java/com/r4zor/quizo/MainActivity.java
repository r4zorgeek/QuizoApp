package com.r4zor.quizo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton mGameSetting;
    private Button mStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FetchCategories().execute();
        mGameSetting = (ImageButton) findViewById(R.id.game_settings);
        mGameSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = SettingsActivity.newIntent(MainActivity.this);
                startActivityForResult(i, 1);
            }
        });

        mStartGame = (Button) findViewById(R.id.startGame);
        mStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = QuestionsActivity.newIntent(MainActivity.this);
                //startActivity(i);
                new FetchQuestions().execute(MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            Toast.makeText(MainActivity.this, "Settings Saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
