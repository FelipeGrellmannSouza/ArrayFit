package com.example.arrayfit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.arrayfit.R;
import com.example.arrayfit.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;

public class TelaCadastro extends AppCompatActivity {
    Usuario usuario;
    FirebaseAuth autenticacao;
    EditText edtNome, edtSenha, edtEmail, edtCelular;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        inicializar();
    }

    //método criado apenas para fins de organização
    private void inicializar(){
        edtNome = findViewById(R.id.edtNome);
        edtSenha = findViewById(R.id.edtSenha);
        edtEmail = findViewById(R.id.edtEmail);
        edtCelular = findViewById(R.id.edtCelular);
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }

    public void voltarTC (View view){
        finish();
    }
}