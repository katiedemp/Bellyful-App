package com.bellyful.bellyfulapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth; //Firebase Auth instance
    private final String TAG = "debug";

    //Init views
    private TextView goToLogin;
    private Button registerButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private ProgressBar registerProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance(); //Initialize Firebase Auth

        initActivity();

    }

    private void initActivity(){

        //Init views
        goToLogin = findViewById(R.id.lblGoToLogin);
        registerButton = findViewById(R.id.btnCreateAccount);
        editTextEmail = findViewById(R.id.editTextNewEmail);
        editTextPassword = findViewById(R.id.editTextNewPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        registerProgressBar = findViewById(R.id.registerProgressBar);

        //Register button click. Checks input is valid then creates an account
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerProgressBar.setVisibility(View.VISIBLE);

                //Get strings
                final String email = editTextEmail.getText().toString();
                final String password = editTextPassword.getText().toString();
                final String confirmPassword = editTextConfirmPassword.getText().toString();

                boolean isValid = validate(email, password, confirmPassword);

                if(isValid){
                    CreateAccount(email, password);
                }else{
                    //Some error occurred
                    registerProgressBar.setVisibility(View.GONE);
                }

            }
        });

        //Takes user to login screen
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start login activity
                Intent startLoginIntent = new Intent(RegisterActivity.this, Login.class);
                RegisterActivity.this.startActivity(startLoginIntent);
            }
        });
    }

    private void CreateAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        registerProgressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "User Created",
                                    Toast.LENGTH_SHORT).show();
                           updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(RegisterActivity.this, "Registration failed.",
//                                    Toast.LENGTH_SHORT).show();
                            //Display relevant error from FirebaseAuth Exceptions
                            try {
                                throw task.getException();
                            } catch(FirebaseAuthWeakPasswordException e) {
                                editTextPassword.setError("Password must be at least 6 characters long");
                                editTextPassword.requestFocus();
                            } catch(FirebaseAuthInvalidCredentialsException e) {
                                editTextEmail.setError("Invalid Email");
                                editTextEmail.requestFocus();
                            } catch(FirebaseAuthUserCollisionException e) {
                                editTextEmail.setError("An account already exists with this email");
                                editTextEmail.requestFocus();
                            } catch(Exception e) {
                                Log.e(TAG, e.getMessage());
                            }
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private boolean validate(String email, String password, String confirmPassword){
        boolean valid = true;

        if (TextUtils.isEmpty(password)) {
            //Empty password
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            valid = false;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            //Empty confirm password
            editTextConfirmPassword.setError("Please confirm password");
            editTextConfirmPassword.requestFocus();
            valid = false;
        }
        if (TextUtils.isEmpty(email)) {
            //Empty email
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            valid = false;
        }
        if(!password.equals(confirmPassword)){
            //Passwords don't match
            editTextPassword.setError("Passwords do not match");
            editTextPassword.requestFocus();
            valid = false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //Invalid email
            editTextEmail.setError("Invalid email");
            editTextEmail.requestFocus();
            valid = false;
        }
        if(password.length()<6){
            //Firebase requires password to be 6 characters long
            editTextPassword.setError("Your password must be at least 6 characters long");
            editTextPassword.requestFocus();
            valid = false;
        }
        return valid;
    }

    public void updateUI(FirebaseUser user){
        if(user != null) { //Take user to MainActivity once registration is complete
            //Start main activity
            Intent startMainActivityIntent = new Intent(RegisterActivity.this, MainActivity.class);
            RegisterActivity.this.startActivity(startMainActivityIntent);
        }else{
            //Something went wrong
        }
    }
}
