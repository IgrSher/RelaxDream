package com.example.hacaton;

import android.os.Bundle;

import android.content.Intent;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hacaton.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToMelodies(View v){
    Intent intent = new Intent(this,MelodiesPage.class);
    startActivity(intent);
    }
    public void goToSongs(View v){
        Intent intent = new Intent(this,Songs.class);
        startActivity(intent);
    }
    public void goToNature(View v){
        Intent intent = new Intent(this,Nature.class);
        startActivity(intent);
    }
}
  