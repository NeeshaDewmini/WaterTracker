package com.example.loginsignuphome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView signupRedirectText, forgotPasswordText;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.Login_email);
        loginPassword = findViewById(R.id.Login_password);
        signupRedirectText = findViewById(R.id.SignupRedirectText);
        forgotPasswordText = findViewById(R.id.ForgotPasswordText);
        loginButton = findViewById(R.id.Login_button);

        db = FirebaseFirestore.getInstance();

        loginButton.setOnClickListener(v -> {
            if (!validateEmail() || !validatePassword()) {
                return;
            }
            checkUser();
        });

        signupRedirectText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });

        forgotPasswordText.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "Forgot Password clicked!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
        });
    }

    public Boolean validateEmail() {
        String val = loginEmail.getText().toString().trim();
        if (val.isEmpty()) {
            loginEmail.setError("Email cannot be empty");
            return false;
        } else {
            loginEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userEmail = loginEmail.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        db.collection("users").whereEqualTo("email", userEmail)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            String passwordFromDB = document.getString("password");
                            if (passwordFromDB != null && passwordFromDB.equals(userPassword)) {
                                // Navigate to HomeActivity after login
                                Intent intent = new Intent(LoginActivity.this, Home.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Prevent going back
                                startActivity(intent);
                                finish();
                            } else {
                                loginPassword.setError("Invalid Credentials");
                            }
                        }
                    } else {
                        loginEmail.setError("User does not exist");
                    }
                })
                .addOnFailureListener(e -> loginEmail.setError("Database Error: " + e.getMessage()));
    }

}
