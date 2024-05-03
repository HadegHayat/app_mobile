package com.example.quizme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Quiz1 extends AppCompatActivity {

    Button next;
    RadioGroup rg;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        next = findViewById(R.id.btnNext);
        rg = findViewById(R.id.radioGroup);

        DatabaseReference questionsRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://quiz-farebase-default-rtdb.firebaseio.com/quizapp/questions/question1");

        questionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String questionText = dataSnapshot.child("texte").getValue(String.class);
                    Log.d("QuestionText", "Question Text: " + questionText);

                    DataSnapshot optionsSnapshot = dataSnapshot.child("options");
                    ArrayList<String> optionsList = new ArrayList<>();
                    for (DataSnapshot optionSnapshot : optionsSnapshot.getChildren()) {
                        String option = optionSnapshot.getValue(String.class);
                        optionsList.add(option);
                    }
                    Log.d("OptionsList", "Options List: " + optionsList.toString());

                    int correctAnswerIndex = dataSnapshot.child("reponse_correcte").getValue(Integer.class);
Log.d("CorrectAnswerIndex", "Correct Answer Index: " + correctAnswerIndex);

                    TextView questionTextView = findViewById(R.id.questionTextView);
                    questionTextView.setText(questionText);


                    rg.removeAllViews();

                    for (int i = 0; i < optionsList.size(); i++) {
                        RadioButton radioButton = new RadioButton(Quiz1.this);
                        radioButton.setText(optionsList.get(i));
                        rg.addView(radioButton);
                    }


                    rg.setTag(correctAnswerIndex);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs de récupération des données
                Toast.makeText(Quiz1.this, "Erreur de récupération des données", Toast.LENGTH_SHORT).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Veuillez choisir une réponse", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedRadioButton = findViewById(rg.getCheckedRadioButtonId());
                    int selectedAnswerIndex = rg.indexOfChild(selectedRadioButton);
                    int correctAnswerIndex = (int) rg.getTag();
                    if (selectedAnswerIndex == correctAnswerIndex) {
                        score++;
                    }
                    Intent i1 = new Intent(getApplicationContext(), Quiz2.class);
                    i1.putExtra("score", score);
                    startActivity(i1);

                }
            }
        });
    }
}
