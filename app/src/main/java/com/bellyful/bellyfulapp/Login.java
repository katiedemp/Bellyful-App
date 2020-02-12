package com.bellyful.bellyfulapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bellyful.bellyfulapp.Model.MealModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private final String TAG = "debug";

    private Button login;
    private TextView registerHere;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance(); //Initialize Firebase Auth

        login = findViewById(R.id.btnLogin);
        registerHere = findViewById(R.id.lblRegisterHere);
        editTextUsername = findViewById(R.id.editTextUserEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginProgressBar = findViewById(R.id.loginProgressBar);

        //Login button click. Checks input is valid then logs user in
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hide the virtual keyboard
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);

                //Show progress bar
                loginProgressBar.setVisibility(View.VISIBLE);

                String usernameText = editTextUsername.getText().toString();
                String passwordText = editTextPassword.getText().toString();

                boolean isValid = validate(usernameText, passwordText);
                if(isValid){
                    signIn(usernameText, passwordText);
                }else{
                    //Some error occurred
                    loginProgressBar.setVisibility(View.GONE);
                }
            }
        });

        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start Register activity
                Intent startRegisterIntent = new Intent(Login.this, RegisterActivity.class);
                Login.this.startActivity(startRegisterIntent);
            }
        });
    }

    private void signIn(String email, final String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginProgressBar.setVisibility(View.GONE);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            loginProgressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //Display relevant error from FirebaseAuth Exceptions
                            try {
                                throw task.getException();
                            } catch(FirebaseAuthInvalidUserException e) {
                                editTextUsername.setError("Invalid email");
//                                editTextUsername.requestFocus();
                            } catch(FirebaseAuthInvalidCredentialsException e) {
                                editTextPassword.setError("Invalid Password");
//                                editTextPassword.requestFocus();
                            } catch(Exception e) {
                                Log.e(TAG, e.getMessage());
                            }
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private boolean validate(String email, String password){
        boolean valid = true;

        if (TextUtils.isEmpty(password)) {
            //Empty password
            editTextPassword.setError("Password required");
//            editTextPassword.requestFocus();
            valid = false;
        }
        if (TextUtils.isEmpty(email)) {
            //Empty email
            editTextUsername.setError("Email required");
//            editTextUsername.requestFocus();
            valid = false;
        }
        return valid;
    }

    private void updateUI(FirebaseUser user){
        if(user != null){
            //Start main activity
            Intent startMainIntent = new Intent(Login.this, MainActivity.class);
            Login.this.startActivity(startMainIntent);
        }else{
            Log.d(TAG, "Something went wrong");
        }
    }


}
