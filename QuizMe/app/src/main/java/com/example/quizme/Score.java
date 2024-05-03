package com.example.quizme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Score extends AppCompatActivity {

    TextView tvscore;
    ProgressBar pb;
    Button btry, blogout;
    int score;
    int totalQuizzes = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        tvscore = findViewById(R.id.textViewScore);
        pb = findViewById(R.id.progressBar);
        btry = findViewById(R.id.btry);
        blogout = findViewById(R.id.buttonLogout);


        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);

        int progress = (score * 100) / totalQuizzes;





        pb.setProgress(progress);
        TextView textViewCircularScore = findViewById(R.id.textViewCircularScore);
        textViewCircularScore.setText(progress + "%");

        btry.setOnClickListener(view -> {

            Intent tryAgainIntent = new Intent(Score.this, Quiz1.class);
            startActivity(tryAgainIntent);
            finish();
        });


        blogout.setOnClickListener(view -> {

            Intent logoutIntent = new Intent(Score.this, MainActivity.class);
            startActivity(logoutIntent);
            finish();
        });
    }
}
