package com.example.quizme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button sign;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        sign=findViewById(R.id.button);
        register=findViewById(R.id.register);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString().trim();
                String userPassword = password.getText().toString().trim();

                if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {

                    auth.signInWithEmailAndPassword(userEmail, userPassword)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Intent i1 = new Intent(MainActivity.this, Quiz1.class);
                                        startActivity(i1);
                                    } else {

                                        Toast.makeText(MainActivity.this, "Erreur d'authentification. Veuillez réessayer.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {

                    Toast.makeText(MainActivity.this, "Veuillez saisir votre email et votre mot de passe.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}