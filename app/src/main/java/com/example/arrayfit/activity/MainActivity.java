package com.example.arrayfit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.arrayfit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnCadastro (View view){
        Intent intent = new Intent(MainActivity.this, TelaCadastro.class);
        startActivity(intent);
    }
    public void btnLogin (View view){
        Intent intent = new Intent(MainActivity.this, TelaLogin.class);
        startActivity(intent);
    }

}