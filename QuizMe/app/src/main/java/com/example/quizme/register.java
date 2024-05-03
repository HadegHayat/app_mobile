package com.example.quizme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    EditText name, email, password, confirmPassword;
    Button registerButton;
    FirebaseAuth MyAuthentification ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getApplicationContext());
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);


        name = findViewById(R.id.editTextTextName);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        confirmPassword = findViewById(R.id.editTextTextConfirmPassword);
        registerButton = findViewById(R.id.button);
        MyAuthentification=FirebaseAuth.getInstance();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String inputName = name.getText().toString().trim();
                String inputEmail = email.getText().toString().trim();
                String inputPassword = password.getText().toString();
                String inputConfirmPassword = confirmPassword.getText().toString();


                if (inputName.isEmpty() || inputEmail.isEmpty() || inputPassword.isEmpty() || inputConfirmPassword.isEmpty()) {
                    Toast.makeText(register.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!inputPassword.equals(inputConfirmPassword)) {
                    Toast.makeText(register.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    return;
                }


                MyAuthentification.createUserWithEmailAndPassword(inputEmail, inputPassword)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(register.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(register.this, MainActivity.class));
                                    finish(); // Close the current activity
                                } else {

                                    Toast.makeText(register.this, "L'inscription a échoué. Veuillez réessayer plus tard.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
