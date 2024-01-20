package com.example.hacaton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MelodiesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melodies_page);
    }
    public void backToMain(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}