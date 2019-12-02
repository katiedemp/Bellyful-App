package com.bellyful.bellyfulapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerHere = findViewById(R.id.lblRegisterHere);
        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start Register activity
                Intent startRegisterIntent = new Intent(Login.this, RegisterActivity.class);
                Login.this.startActivity(startRegisterIntent);
            }
        });
    }
}
