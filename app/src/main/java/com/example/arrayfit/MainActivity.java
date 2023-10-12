package com.example.arrayfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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