package com.bellyful.bellyfulapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start login activity
        Intent startLoginIntent = new Intent(MainActivity.this, Login.class);
        MainActivity.this.startActivity(startLoginIntent);

    }

}
